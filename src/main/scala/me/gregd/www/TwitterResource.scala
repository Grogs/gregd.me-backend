package me.gregd.www

import javax.ws.rs._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters
import scala.collection.JavaConversions._
import me.gregd.www.model.Tweet
import me.gregd.www.data.TweetDAO

@Path("/twitter")
class Twitter {
val dao = new TweetDAO

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def all(@QueryParam("since") since:String):java.util.List[Tweet] = {
  	dao.addTweet(new Tweet(2,"greg","test"))
    dao.getTweets()
  }
  
}