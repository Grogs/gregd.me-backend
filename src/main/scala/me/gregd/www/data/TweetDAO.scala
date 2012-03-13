package me.gregd.www.data
import javax.persistence._
import me.gregd.www.model.Tweet

class TweetDAO {

  var factory = Persistence.createEntityManagerFactory("dev")

  def em():EntityManager = {
    factory.createEntityManager();
  }

  
  def getTweets() = {
    val queryString = "SELECT t FROM Tweet t"
    em().createQuery(queryString).getResultList().asInstanceOf[java.util.List[Tweet]]
  }
  
}


  