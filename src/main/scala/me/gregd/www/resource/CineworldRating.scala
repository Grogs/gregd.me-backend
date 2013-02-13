package me.gregd.www.resource

import javax.ws.rs._
import java.util._
import java.io._
import javax.mail._
import javax.mail.internet._
import javax.ws.rs.core.MediaType
import scala.collection.JavaConverters
import scala.collection.JavaConversions._
import me.gregd.www.model._
import me.gregd.www.data._
import java.net.URL
import java.net.URLEncoder
import javax.ws.rs.core.Context
import javax.servlet.http.HttpServletResponse
import java.util.{ List => JList }
import org.apache.http.client.HttpClient
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.params.HttpProtocolParams
import scala.io.Source
import com.redis._
import com.redis.serialization.Parse
import Parse.Implicits._
import com.redis.serialization.Format
import MediaType._
import me.gregd.www.model.cineworld._
import javax.xml.bind.JAXBContext


@Path( "/cineworldRating" )
class CineworldRating {
  
  val (toJson, fromJson) = {
    val jc = JAXBContext.newInstance(classOf[Movie])
    val marshaller = jc.createMarshaller
    val unmarshaller = jc.createUnmarshaller
    def unmarshall[T](json : String) = {
          unmarshaller.unmarshal(new StringReader(json)).asInstanceOf[T]
        }
    (
        {
          marshaller
        },
        unmarshall _
    )
  }

  import URLEncoder.{ encode => urlencode }
  
  
  val redis = new RedisClient("localhost", 6379)


  @POST
  @Consumes( Array( APPLICATION_FORM_URLENCODED , APPLICATION_JSON ) )
  @Produces( Array( APPLICATION_JSON ) )
  def submit( @FormParam( "movies" ) movies: JList[String], @Context servletResponse: HttpServletResponse ) = {
    servletResponse.setHeader( "Access-Control-Allow-Origin", "*" ) //CORS
    movies map { title =>
      getMovie(getMovieId(title))
      
    }
  }

  def getOrInsert[T](key:String)(f: => T):T = {
    /*redis.get[T](key) match {
      case Some(t) => t
      case None => {
        val res = f
        redis.set(key, res)
        res
      }
    }*/
      null.asInstanceOf[T]
  }
  
  def getMovie( id: String ) = {
    getOrInsert(s"movie-$id"){

	    val request = s"http://imdbapi.org/?id=$id&type=xml&plot=simple&episode=1&lang=en-US&aka=simple&release=simple"
	    val response = xml.XML.load( request ) \ "IMDBDocument"
	
	    val rating = response \ "rating" text
	    val votes = response \ "rating_count" text
	    val url = response \ "imdb_url" text
	
	    Movie( id, rating, votes, url )
    }
  }

  def getMovieId( title: String ) = {
    getOrInsert(s"title-$title"){
    
	    //3rd party imdb api's have terrible search functionality
	    //Which is a shame, as 3rd party apis would have allow-origin *
	    //and i wouldn't need to do this on my server :(
	
	    val url = s"http://www.imdb.com/xml/find?xml=1&nr=1&tt=on&q=${urlencode( title )}"
	    val results = xml.XML.loadString( getUrl(url) )
	    println(results)
	    ( results \ "ResultSet" \ "ImdbEntity" head ).attribute("id").map(_.text) orNull
  
    }
  }

  def getUrl( url: String ) = {
    val userAgent = """Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.101 Safari/537.11"""
    val httpClient = new DefaultHttpClient
    HttpProtocolParams.setUserAgent(httpClient.getParams(), userAgent);
    val req = new HttpGet( url )
    val resp = httpClient.execute( req )
    val is = resp.getEntity.getContent
    Source.fromInputStream(is).mkString
  }
  
}

