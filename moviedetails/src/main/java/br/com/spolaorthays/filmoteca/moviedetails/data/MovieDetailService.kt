package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    //https://api.themoviedb.org/3/movie/298618?language=pt-BR
    @GET("/3/movie/{id}?language=pt-BR")
    suspend fun getMovieDetail(@Path("id") id: Int): MovieDetail
}