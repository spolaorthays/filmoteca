package br.com.spolaorthays.filmoteca.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "")
    val name: String
)