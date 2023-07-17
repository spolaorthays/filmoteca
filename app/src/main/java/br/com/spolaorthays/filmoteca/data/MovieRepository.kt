package br.com.spolaorthays.filmoteca.data

import br.com.spolaorthays.filmoteca.data.model.NowPlaying
import io.reactivex.Single
import javax.inject.Inject

interface MovieRepository {
    fun getNowPlaying(): Single<NowPlaying>
}

class MovieRepositoryImpl @Inject constructor(val service: MovieService) : MovieRepository {
    override fun getNowPlaying(): Single<NowPlaying> {
        return service.getNowPlayingMovies()
    }
}