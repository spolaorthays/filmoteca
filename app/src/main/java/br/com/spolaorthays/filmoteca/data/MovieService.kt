package br.com.spolaorthays.filmoteca.data

import br.com.spolaorthays.filmoteca.data.model.MovieResponse
import br.com.spolaorthays.filmoteca.data.model.NowPlaying
import io.reactivex.Single
import retrofit2.http.GET

interface MovieService {
    @GET
    fun getListMovies(): MovieResponse

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(): Single<NowPlaying>
}