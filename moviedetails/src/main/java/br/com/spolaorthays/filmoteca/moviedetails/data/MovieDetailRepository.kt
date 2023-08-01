package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.shared.model.Movie
import io.reactivex.Single
import javax.inject.Inject

interface MovieDetailRepository {
    fun getMovieDetail(id: Int): Single<Movie>
}

class MovieDetailRepositoryImpl @Inject constructor(private val service: MovieDetailService) :
    MovieDetailRepository {
    override fun getMovieDetail(id: Int): Single<Movie> {
        return service.getMovieDetail(id)
    }

}