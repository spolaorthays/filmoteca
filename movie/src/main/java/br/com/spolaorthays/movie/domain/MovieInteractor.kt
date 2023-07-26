package br.com.spolaorthays.movie.domain

import br.com.spolaorthays.movie.data.MovieRepository
import br.com.spolaorthays.movie.data.model.Movie
import io.reactivex.Single
import javax.inject.Inject

interface MovieInteractor {
    fun getMovies(url: String): Single<List<Movie>>
}

class MovieInteractorImpl @Inject constructor(private val repository: MovieRepository) : MovieInteractor {

    override fun getMovies(url: String): Single<List<Movie>> {
        return repository.getAllMovies(url).map {
            it.results
        }
    }

}