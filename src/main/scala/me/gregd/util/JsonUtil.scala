package me.gregd.util


import java.io.StringWriter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.`type`.TypeReference

object JsonUtil {

  def module = DefaultScalaModule

  def mapper = {
    val result = new ObjectMapper
    result.registerModule( module )
    result
  }

  
  
  
  def serialize( value: Any ): String = {
    val writer = new StringWriter()
    mapper.writeValue( writer, value )
    writer.toString
  }

  def deserialize[T: Manifest]( value: String ): T =
    mapper.readValue( value, typeReference[T] )

    
    
    
  private[this] def typeReference[T: Manifest] = new TypeReference[T] {
    override def getType = typeFromManifest( manifest[T] )
  }

  private[this] def typeFromManifest( m: Manifest[_] ): Type = {
    if ( m.typeArguments.isEmpty ) { m.erasure }
    else new ParameterizedType {
      def getRawType = m.erasure

      def getActualTypeArguments = m.typeArguments.map( typeFromManifest ).toArray

      def getOwnerType = null
    }
  }

}