package me.gregd.util

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import java.lang.System._
import scala.util.Try

object JettyStart {
  def createServer = {
    val port = Try(getProperty("JETTY_PORT").toInt) getOrElse 8080
	  val server = new Server( port )
	
	  val context = new WebAppContext()
	  context setContextPath "/"
	  context.setResourceBase( "src/main/webapp" )
	  context.setDescriptor( "WEB-INF/web.xml" )

    server.setHandler(context)

	  server
  }
  
  def main(args: Array[String]) = {
    val server = createServer
	  server.start()
    server.join()
  }
}