package br.com.spolaorthays.filmoteca.shared.model

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
    const val POST_WIDTH_LIST_SIZE = 400
    const val POST_HEIGHT_LIST_SIZE = 600
    const val POST_WIDTH_DETAIL_SIZE = 800
    const val POST_HEIGHT_DETAIL_SIZE = 1200

    // Base image url
    const val BASE_IMAGE_LINK = "https://image.tmdb.org/t/p/original/"

    // Error or Invalid data
    const val NOT_AVAILABLE = "Não disponível"

    // Date Formats
    const val MOVIE_API_FORMAT = "yyyy-MM-dd"
    const val MY_PROJECT_FORMAT = "dd/MM/yyyy"
    const val DOLLAR_API_REQUEST_FORMAT = "MM-dd-yyyy"

    // API Format Response
    const val JSON_FORMAT = "json"
}