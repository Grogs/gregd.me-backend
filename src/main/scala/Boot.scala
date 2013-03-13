import akka.actor.ActorSystem
import spray.io.IOBridge
import akka.actor.Props
import me.gregd.www.resource.AllServicesActor
import spray.can.server.HttpServer
import spray.io.SingletonHandler

object Boot extends App {
//  val system = ActorSystem("gregd")
//
//  val ioBridge = new IOBridge(system).start()
//
//  val service = system.actorOf(Props[AllServicesActor])
//
//  val httpServer = system.actorOf(Props(new HttpServer(ioBridge, SingletonHandler(service))))
//
//  httpServer ! HttpServer.Bind("localhost", 8080)
//
//  system.registerOnTermination(ioBridge.stop)
}