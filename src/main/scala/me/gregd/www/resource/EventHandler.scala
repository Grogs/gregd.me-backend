package me.gregd.www.resource

import org.eclipse.jetty.websocket.api.annotations.{OnWebSocketMessage,WebSocket}
import me.gregd.util.JsonUtil
import me.gregd.www.model.cineworld.Event
import me.gregd.www.model.cineworld.Movie
import org.eclipse.jetty.websocket.api.annotations.WebSocket


@WebSocket
class EventHandler  {
  
  @OnWebSocketMessage
  def handle(message: String) = {
    JsonUtil.deserialize[Event](message) match {
      case movie:Movie => {
        println(movie)
      }
    }
  }

}