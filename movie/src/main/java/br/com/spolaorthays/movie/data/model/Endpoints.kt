package br.com.spolaorthays.movie.data.model

object Endpoints {
    private const val API_VERSION = "/3/"
    const val END_NOW_PLAYING = "${API_VERSION}movie/now_playing"
    const val END_POPULARS = "${API_VERSION}movie/popular"
    const val END_TOP_RATED = "${API_VERSION}movie/top_rated"
    const val END_UPCOMING = "${API_VERSION}movie/upcoming"
}