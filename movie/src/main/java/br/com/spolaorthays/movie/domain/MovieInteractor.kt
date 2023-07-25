package br.com.spolaorthays.movie.domain

import br.com.spolaorthays.movie.data.MovieRepository
import br.com.spolaorthays.movie.data.model.Movie
import io.reactivex.Single
import javax.inject.Inject

interface MovieInteractor {
    fun getNowPlaying(): Single<List<Movie>>
    fun getPopulars(): Single<List<Movie>>
}

class MovieInteractorImpl @Inject constructor(private val repository: MovieRepository) : MovieInteractor {

    override fun getNowPlaying(): Single<List<Movie>> {
        return repository.getNowPlaying().map {
            it.results
        }
    }

    override fun getPopulars(): Single<List<Movie>> {
        return repository.getPopulars().map {
            it.results
        }
    }

}