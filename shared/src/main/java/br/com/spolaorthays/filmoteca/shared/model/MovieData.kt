package br.com.spolaorthays.filmoteca.shared.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Now Playing Json
val jsonMovie = "{\n" +
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

val jsonMovieDetail = "{\n" +
        "   \"adult\":false,\n" +
        "   \"backdrop_path\":\"/yF1eOkaYvwiORauRCPWznV9xVvi.jpg\",\n" +
        "   \"belongs_to_collection\":null,\n" +
        "   \"budget\":190000000,\n" +
        "   \"genres\":[\n" +
        "      {\n" +
        "         \"id\":28,\n" +
        "         \"name\":\"Ação\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"id\":12,\n" +
        "         \"name\":\"Aventura\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"id\":878,\n" +
        "         \"name\":\"Ficção científica\"\n" +
        "      }\n" +
        "   ],\n" +
        "   \"homepage\":\"https://www.theflashfilme.com.br\",\n" +
        "   \"id\":298618,\n" +
        "   \"imdb_id\":\"tt0439572\",\n" +
        "   \"original_language\":\"en\",\n" +
        "   \"original_title\":\"The Flash\",\n" +
        "   \"overview\":\"Quando Barry usa seus superpoderes para viajar no tempo e mudar os eventos do passado. Mas quando tenta salvar sua família e acaba, sem querer, alterando o futuro, Barry fica preso em uma realidade na qual o General Zod está de volta, ameaçando colocar o mundo em risco, e não há super-heróis a quem recorrer.\",\n" +
        "   \"popularity\":5125.277,\n" +
        "   \"poster_path\":\"/itYONYDHpJqTuu8BCXAtHxgpglq.jpg\",\n" +
        "   \"production_companies\":[\n" +
        "      {\n" +
        "         \"id\":174,\n" +
        "         \"logo_path\":\"/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png\",\n" +
        "         \"name\":\"Warner Bros. Pictures\",\n" +
        "         \"origin_country\":\"US\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"id\":152809,\n" +
        "         \"logo_path\":null,\n" +
        "         \"name\":\"Double Dream\",\n" +
        "         \"origin_country\":\"US\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"id\":170335,\n" +
        "         \"logo_path\":null,\n" +
        "         \"name\":\"The Disco Factory\",\n" +
        "         \"origin_country\":\"US\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"id\":128064,\n" +
        "         \"logo_path\":\"/13F3Jf7EFAcREU0xzZqJnVnyGXu.png\",\n" +
        "         \"name\":\"DC Films\",\n" +
        "         \"origin_country\":\"US\"\n" +
        "      }\n" +
        "   ],\n" +
        "   \"production_countries\":[\n" +
        "      {\n" +
        "         \"iso_3166_1\":\"US\",\n" +
        "         \"name\":\"United States of America\"\n" +
        "      }\n" +
        "   ],\n" +
        "   \"release_date\":\"2023-06-13\",\n" +
        "   \"revenue\":267481043,\n" +
        "   \"runtime\":144,\n" +
        "   \"spoken_languages\":[\n" +
        "      {\n" +
        "         \"english_name\":\"English\",\n" +
        "         \"iso_639_1\":\"en\",\n" +
        "         \"name\":\"English\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"english_name\":\"Spanish\",\n" +
        "         \"iso_639_1\":\"es\",\n" +
        "         \"name\":\"Español\"\n" +
        "      },\n" +
        "      {\n" +
        "         \"english_name\":\"Russian\",\n" +
        "         \"iso_639_1\":\"ru\",\n" +
        "         \"name\":\"Pусский\"\n" +
        "      }\n" +
        "   ],\n" +
        "   \"status\":\"Released\",\n" +
        "   \"tagline\":\"Os mundos colidem.\",\n" +
        "   \"title\":\"The Flash\",\n" +
        "   \"video\":false,\n" +
        "   \"vote_average\":6.935,\n" +
        "   \"vote_count\":1839\n" +
        "}"

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "dates")
    val dates: MovieDates? = null,
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
    val backdropPath: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val movieId: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val movieDescription: String,
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

@JsonClass(generateAdapter = true)
data class MovieGenre(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class MovieLanguage(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val acronym: String,
    @Json(name = "name")
    val languageName: String
)

data class MovieBelongsCollection(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backdropPath: String
)

@JsonClass(generateAdapter = true)
data class MovieDetail(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: MovieBelongsCollection?,
    @Json(name = "budget")
    val budget: Long,
    @Json(name = "genres")
    val genres: List<MovieGenre>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val movieId: Int,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val movieDescription: String,
    @Json(name = "popularity")
    val popularity: Float,
    @Json(name = "poster_path")
    val posterImagePath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "revenue")
    val revenue: Long,
    @Json(name = "runtime")
    val runtime: Long,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<MovieLanguage>,
    @Json(name = "status")
    val movieStatus: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val movieTitle: String,
    @Json(name = "video")
    val hasVideo: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
)

