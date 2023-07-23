package br.com.spolaorthays.movie.data.remote

import br.com.spolaorthays.movie.data.model.MovieResponse
import br.com.spolaorthays.movie.data.model.NowPlaying
import io.reactivex.Single
import retrofit2.http.GET

interface MovieService {
    @GET
    fun getListMovies(): MovieResponse

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(): Single<NowPlaying>
}