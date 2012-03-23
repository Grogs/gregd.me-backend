package me.gregd.www.data
import javax.persistence._
import me.gregd.www.model.Tweet

object TweetDAO {

  var tweets = collection.Map[Long, Tweet]()

  def all = tweets.values.toSeq
  
  def apply(id:Int) = tweets(id)
  
  def apply() = all
  
  def getTweet(id:Int) = tweets(id)
  
  def update(id:Long, tweet:Tweet) = {
    tweets = tweets.updated(id, tweet)
  }
  
  def add(newTweets:List[Tweet]) = {
    newTweets.foreach{t => update(t.id,t)}
  }
  
  val timer=new java.util.Timer
  timer.scheduleAtFixedRate(new java.util.TimerTask{
	  def run = updateTweets
  },0 , 10000)

  def updateTweets = {
	val tweetsXML = xml.XML.load("https://api.twitter.com/1/statuses/user_timeline.xml?include_entities=true&include_rts=true&screen_name=grogs&count=10") 
	val tweets = (tweetsXML \ "status") map { elem =>
	  new Tweet(
		  (elem \ "id" text).toLong,
		  (elem \ "user" \ "screen_name" text),
		  (elem \ "text" text)
	  )
	}
	tweets.foreach(t =>
		this(t.id) = t
	)
  }
}



  