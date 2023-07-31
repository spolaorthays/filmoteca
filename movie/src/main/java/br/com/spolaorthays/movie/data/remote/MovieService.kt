package br.com.spolaorthays.movie.data.remote

import br.com.spolaorthays.filmoteca.shared.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url


interface MovieService {
    @GET
    fun getAllMovieLists(@Url url: String): Single<MovieResponse>
}