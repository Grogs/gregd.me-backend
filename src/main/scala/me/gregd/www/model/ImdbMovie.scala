package me.gregd.www.model

import me.gregd.www.model.cineworld.Movie

trait Imdb { //self : Movie =>
  val rating : String
  val votes : String
  val imdb_url : String
}