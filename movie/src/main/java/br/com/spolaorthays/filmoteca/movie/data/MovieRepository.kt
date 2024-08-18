package br.com.spolaorthays.filmoteca.movie.data

import br.com.spolaorthays.filmoteca.shared.model.MovieResponse
import br.com.spolaorthays.filmoteca.movie.data.remote.MovieService
import javax.inject.Inject

interface MovieRepository {
    suspend fun getAllMovies(url: String): MovieResponse
}

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieRepository {

    override suspend fun getAllMovies(url: String): MovieResponse {
        return service.getAllMovieLists(url)
    }
}