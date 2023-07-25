package br.com.spolaorthays.movie.data.remote

import br.com.spolaorthays.movie.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

internal const val API_VERSION = "/3/"
interface MovieService {
    @GET("${API_VERSION}movie/popular")
    fun getPopularMovies(): Single<MovieResponse>

    @GET("${API_VERSION}movie/now_playing")
    fun getNowPlayingMovies(): Single<MovieResponse>
}