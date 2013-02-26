package me.gregd.www.model.cineworld

case class Cinema(
    id: Int,
    name: String,
    cinema_url: String,
    telephone: String
) extends Event