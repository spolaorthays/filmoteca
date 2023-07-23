package br.com.spolaorthays.movie.data

import br.com.spolaorthays.movie.data.model.NowPlaying
import br.com.spolaorthays.movie.data.remote.MovieService
import io.reactivex.Single
import javax.inject.Inject

interface MovieRepository {
    fun getNowPlaying(): Single<NowPlaying>
}

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieRepository {
    override fun getNowPlaying(): Single<NowPlaying> {
        return service.getNowPlayingMovies()
    }
}