package me.gregd.www.data
import javax.persistence._
import me.gregd.www.model.Tweet

object TweetDAO {

  @PersistenceContext
  var em:EntityManager = _

  
  def getTweets() {
    val queryString = "SELECT * FROM tweets"
    em.createQuery(queryString).getResultList().asInstanceOf[java.util.List[Tweet]]
  }
  
}