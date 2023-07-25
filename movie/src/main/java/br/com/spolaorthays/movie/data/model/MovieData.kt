package br.com.spolaorthays.movie.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Now Playing Json
val json = "{\n" +
        "   \"dates\":{\n" +
        "      \"maximum\":\"2023-07-15\",\n" +
        "      \"minimum\":\"2023-05-28\"\n" +
        "   },\n" +
        "   \"page\":1,\n" +
        "   \"results\":[\n" +
        "      {\n" +
        "         \"adult\":false,\n" +
        "         \"backdrop_path\":\"/qWQSnedj0LCUjWNp9fLcMtfgadp.jpg\",\n" +
        "         \"genre_ids\":[\n" +
        "            28,\n" +
        "            12,\n" +
        "            878\n" +
        "         ],\n" +
        "         \"id\":667538,\n" +
        "         \"original_language\":\"en\",\n" +
        "         \"original_title\":\"Transformers: Rise of the Beasts\",\n" +
        "         \"overview\":\"When a new threat capable of destroying the entire planet emerges, Optimus Prime and the Autobots must team up with a powerful faction known as the Maximals. With the fate of humanity hanging in the balance, humans Noah and Elena will do whatever it takes to help the Transformers as they engage in the ultimate battle to save Earth.\",\n" +
        "         \"popularity\":10601.844,\n" +
        "         \"poster_path\":\"/gPbM0MK8CP8A174rmUwGsADNYKD.jpg\",\n" +
        "         \"release_date\":\"2023-06-06\",\n" +
        "         \"title\":\"Transformers: Rise of the Beasts\",\n" +
        "         \"video\":false,\n" +
        "         \"vote_average\":7.3,\n" +
        "         \"vote_count\":1215\n" +
        "      }\n" +
        "   ]\n" +
        "}"

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "dates")
    val dates: MovieDates,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Movie>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class MovieDates(
    @Json(name = "maximum")
    val max: String,
    @Json(name = "minimum")
    val min: String
)

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val movieId: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val movieDescription:String,
    @Json(name = "popularity")
    val popularity: Float,
    @Json(name = "poster_path")
    val posterImagePath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "title")
    val movieTitle: String,
    @Json(name = "video")
    val hasVideo: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
)