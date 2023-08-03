package br.com.spolaorthays.filmoteca.movie.data

import br.com.spolaorthays.filmoteca.shared.model.MovieResponse
import br.com.spolaorthays.filmoteca.movie.data.remote.MovieService
import io.reactivex.Single
import javax.inject.Inject

interface MovieRepository {
    fun getAllMovies(url: String): Single<MovieResponse>
}

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieRepository {

    override fun getAllMovies(url: String): Single<MovieResponse> {
        return service.getAllMovieLists(url)
    }
}