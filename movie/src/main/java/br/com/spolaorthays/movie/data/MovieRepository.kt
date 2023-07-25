package br.com.spolaorthays.movie.data

import br.com.spolaorthays.movie.data.model.MovieResponse
import br.com.spolaorthays.movie.data.remote.MovieService
import io.reactivex.Single
import javax.inject.Inject

interface MovieRepository {
    fun getNowPlaying(): Single<MovieResponse>
}

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieRepository {
    override fun getNowPlaying(): Single<MovieResponse> {
        return service.getNowPlayingMovies()
    }
}