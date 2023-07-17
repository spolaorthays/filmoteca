package br.com.spolaorthays.filmoteca.data

import br.com.spolaorthays.filmoteca.data.model.MovieResponse
import retrofit2.http.GET

interface MovieService {
    @GET
    fun getListMovies(): MovieResponse
}