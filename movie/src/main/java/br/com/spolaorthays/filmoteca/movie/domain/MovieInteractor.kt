package br.com.spolaorthays.filmoteca.movie.domain

import br.com.spolaorthays.filmoteca.movie.data.MovieRepository
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.END_NOW_PLAYING
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.END_POPULARS
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.END_TOP_RATED
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.END_UPCOMING
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.NOW_PLAYING
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.POPULARS
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.TOP_RATED
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.UPCOMING
import br.com.spolaorthays.filmoteca.shared.model.Movie
import io.reactivex.Single
import javax.inject.Inject

interface MovieInteractor {
    fun getMovies(url: String): Single<List<Movie>>

    fun getEndpointList(): List<String>
    fun getSessionList(): List<String>
}

class MovieInteractorImpl @Inject constructor(private val repository: MovieRepository) :
    MovieInteractor {

    override fun getMovies(url: String): Single<List<Movie>> {
        return repository.getAllMovies(url).map {
            it.results
        }
    }

    override fun getEndpointList(): List<String> {
       return listOf(
            END_NOW_PLAYING,
            END_POPULARS,
            END_TOP_RATED,
            END_UPCOMING
        )
    }

    override fun getSessionList(): List<String> {
        return listOf(
            NOW_PLAYING,
            POPULARS,
            TOP_RATED,
            UPCOMING
        )
    }

}