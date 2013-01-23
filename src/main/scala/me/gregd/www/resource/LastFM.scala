package me.gregd.www.resource

import javax.ws.rs._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters._
import java.{util=>ju}
import me.gregd.www.model._
import me.gregd.www.data._

@Path("/lastfm")
class LastFM {
  
  val scrobbles = ScrobblesDAO

  @GET
  @Path("/albums")
  @Produces(Array(MediaType.TEXT_HTML))
  def getAlbums = {
    <div>
	  {scrobbles().take(15).map(_.image).distinct map { img =>
	    <img class="lastfm album-cover" src={ img }/>
	  }}
    </div>.toString
  }

  
  @GET
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def all(@QueryParam("since") since:String) = {
    scrobbles().asJava 
  }

  @POST
  @Path("/{id}") 
  @Consumes(Array( MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def update(@PathParam("id") id:Int, s:Scrobble) = {
    scrobbles(id) = s
  }
  
  @GET
  @Path("/{id}") 
  @Produces(Array(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML))
  def get(@PathParam("id") id:Int ) = {
    scrobbles(id)
  }
  
}

