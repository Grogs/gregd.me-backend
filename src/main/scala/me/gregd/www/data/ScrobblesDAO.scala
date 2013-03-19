package me.gregd.www.data
import javax.persistence._
import me.gregd.www.model.Scrobble
import util.Try
import java.util.Date

object ScrobblesDAO {

  var scrobbles = collection.Map[Long, Scrobble]()

  def all = scrobbles.values.toList
  
  def apply(id:Int) = scrobbles(id)
  
  def apply() = all
  
  def getTweet(id:Int) = scrobbles(id)
  
  def update(id:Long, tweet:Scrobble) = {
    scrobbles = scrobbles.updated(id, tweet)
  }
  
  def add(newTweets:List[Scrobble]) = {
    newTweets.foreach{t => update(t.uts,t)}
  }
  
  val timer=new java.util.Timer
  timer.scheduleAtFixedRate(new java.util.TimerTask{
	  def run = updateTweets
  },0 , 10000)

  def updateTweets = {
	val scrobblesXML = xml.XML.load("http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=grogs&api_key=140959837de6932676a039ab4b312cb1")
	val scrobbles = (scrobblesXML \ "recenttracks" \ "track") map { track =>
	  new Scrobble(
		  Try((track \ "date" \ "@uts").text.toLong).getOrElse(0),
		  (track \ "name" text),
		  (track \ "album" text),
		  track \ "artist" text,
		  ((track \ "image").filter(_.attributes.exists(_.value.text equals "large")).text),
		  Try(track \ "date" text).getOrElse("")
	  )
	}
	scrobbles.foreach(s =>
		this(s.uts) = s
	)
  }
}



  