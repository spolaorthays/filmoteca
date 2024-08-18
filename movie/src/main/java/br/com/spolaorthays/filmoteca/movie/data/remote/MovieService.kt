package br.com.spolaorthays.filmoteca.movie.data.remote

import br.com.spolaorthays.filmoteca.shared.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface MovieService {
    @GET
    suspend fun getAllMovieLists(@Url url: String): MovieResponse
}