package me.gregd.www

import sjson.json.JsonSerialization
import sjson.json.Serializer.SJSON
import scala.util.Try


trait JsonSupport {
  def unapply(json:String) = Try(SJSON.in(json)) toOption
  def toJson = SJSON.toJSON(this)
}