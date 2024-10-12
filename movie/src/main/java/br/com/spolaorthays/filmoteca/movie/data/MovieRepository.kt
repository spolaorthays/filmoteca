package br.com.spolaorthays.filmoteca.movie.data

import br.com.spolaorthays.filmoteca.shared.model.MovieResponse
import br.com.spolaorthays.filmoteca.movie.data.remote.MovieService
import br.com.spolaorthays.filmoteca.shared.schedulers.AppCoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieRepository {
    suspend fun getAllMovies(url: String): MovieResponse
}

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dispatcher: AppCoroutineDispatcher,
) : MovieRepository {

    override suspend fun getAllMovies(url: String): MovieResponse = withContext(dispatcher.ioScheduler) {
        service.getAllMovieLists(url)
    }

}