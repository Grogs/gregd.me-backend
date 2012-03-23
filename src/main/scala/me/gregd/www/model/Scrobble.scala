package me.gregd.www.model
import javax.xml.bind.annotation.XmlRootElement;
import scala.reflect.BeanProperty
import javax.persistence._

@Entity
@XmlRootElement
class Scrobble (
  
  @Id @Basic(optional = false)
  @BeanProperty var uts:Long,
  
  @BeanProperty var song:String,
  @BeanProperty var album:String,
  @BeanProperty var artist:String,
  @BeanProperty var time:String

) {def this() = {this(0,"","","","")}}