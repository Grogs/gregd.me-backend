package me.gregd.www.model.cineworld

import me.gregd.www.JsonSupport
import scala.beans.BeanInfo

@BeanInfo
case class Movie (
  id:Int,
  title:String,
  poster_ur:String
) extends JsonSupport {
  def this() = this(0,"","")
}