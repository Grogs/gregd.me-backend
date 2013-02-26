package me.gregd.www.model.cineworld

import me.gregd.www.JsonSupport
import scala.beans.BeanInfo

case class Movie (
  id:Int,
  title:String,
  poster_ur:String
) extends Event