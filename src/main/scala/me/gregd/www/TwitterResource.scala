package me.gregd.www

import javax.ws.rs._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters
import scala.collection.JavaConversions._
import me.gregd.www.model.Tweet

@Path("/twitter")
class Twitter {

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def all(@QueryParam("since") since:String):java.util.List[Tweet] = {
    List(new Tweet(1,"grogs","test"))
  }
  
}