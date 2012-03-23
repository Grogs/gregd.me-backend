package me.gregd.www.data
import javax.persistence._
import me.gregd.www.model.Scrobble

object ScrobblesDAO {

  var scrobbles = collection.Map[Long, Scrobble]()

  def all = scrobbles.values.toSeq
  
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
	val scrobblesXML = xml.XML.load("http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=grogs&api_key=b25b959554ed76058ac220b7b2e0a026") 
	val scrobbles = (scrobblesXML \ "recenttracks" \ "track") map { track =>
	  new Scrobble(
		  (track \ "date" \ "@uts" text).toLong,
		  (track \ "name" text),
		  (track \ "album" text),
		  track \ "artist" text,
		  track \ "date" text
	  )
	}
	scrobbles.foreach(s =>
		this(s.uts) = s
	)
  }
}



  