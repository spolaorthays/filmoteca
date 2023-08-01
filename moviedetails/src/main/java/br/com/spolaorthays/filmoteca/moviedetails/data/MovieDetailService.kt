package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.shared.model.Movie
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET

interface MovieDetailService {

    //https://api.themoviedb.org/3/movie/298618?language=pt-BR
    @GET("/3/movie/{id}?language=pt-BR")
    fun getMovieDetail(@Field("id") id: Int): Single<Movie>
}