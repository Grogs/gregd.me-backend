package me.gregd.www.data
import javax.persistence._

object TweetDAO {

  @PersistenceContext
  var em:EntityManager = _

}