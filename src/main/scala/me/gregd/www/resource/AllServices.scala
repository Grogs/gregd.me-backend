package me.gregd.www.resource

import akka.actor.Actor
import spray.routing.HttpService
import akka.actor.ActorSystem
import akka.actor.Props
import spray.io.IOBridge
import spray.can.server.HttpServer
import spray.io.SingletonHandler

class AllServicesActor extends Actor with AllServices {
  def actorRefFactory = context
  def receive = runRoute( mainRoute ~ lastfmRoute )
}

trait AllServices extends HttpService {
//  def get_[T <: AnyRef](url:String)(fun: => T) =     path( url ) {
//      get {
//        dynamic {
//        	complete(fun)
//        }
//      }
//    }
  
  def mainRoute = {
    path( "index" ) {
      get {
        dynamic {
        	complete( "Hello World!" )
        }
      }
    }
  }
  def lastfmRoute = {
    path( "index" ) {
      get {
        dynamic {
        	complete( "Hello World!" )
        }
      }
    }
  }  
  
//  def rating = {
//    path( "rating" ) {
//      post { req =>
//        dynamic { entity(as[String]) {
//        	complete { movie:String =>
//        	  val requestedMovie = this
//        	  movie
//        	  ""
//        	}
//          
//        }
//        }
//      }
//    }
//  }
}


