package br.com.spolaorthays.movie.data.model

object Constants {

    // Endpoints
    private const val API_VERSION = "/3/"
    const val END_NOW_PLAYING = "${API_VERSION}movie/now_playing"
    const val END_POPULARS = "${API_VERSION}movie/popular"
    const val END_TOP_RATED = "${API_VERSION}movie/top_rated"
    const val END_UPCOMING = "${API_VERSION}movie/upcoming"

    // Sessions
    const val NOW_PLAYING = "Mais vistos agora"
    const val POPULARS = "Populares"
    const val TOP_RATED = "Mais votados"
    const val UPCOMING = "Em breve"

    // Posters sizes
    const val WIDTH_SIZE = 400
    const val HEIGHT_SIZE = 600
}