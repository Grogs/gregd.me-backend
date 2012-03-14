package me.gregd.www.model
import javax.xml.bind.annotation.XmlRootElement;
import scala.reflect.BeanProperty
import javax.persistence._

@Entity
@XmlRootElement
class Tweet (
  
  @Id @Basic(optional = false)
  @BeanProperty var id:Integer,
  
  @Column(name="screenname")
  @BeanProperty var screenName:String,
  
  @BeanProperty var body:String

) {def this() = {this(0,"","")}}