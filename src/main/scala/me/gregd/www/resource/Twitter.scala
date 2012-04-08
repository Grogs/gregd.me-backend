package me.gregd.www.resource

import javax.ws.rs._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters._
import me.gregd.www.model.Tweet
import me.gregd.www.data.TweetDAO

@Path("/twitter")
class Twitter {
  
  val tweets = TweetDAO

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def all(@QueryParam("since") since:String) = {
    tweets.all.asJava
  }

  @POST
  @Path("/{id}") 
  @Consumes(Array( MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def update(@PathParam("id") id:Int, t:Tweet) = {
    tweets(id) = t
  }
  
  @GET
  @Path("/{id}") 
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def get(@PathParam("id") id:Int ) = {
    tweets(id)
  }
  
}

