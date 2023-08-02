package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

interface MovieDetailRepository {
    fun getMovieDetail(id: Int): Single<MovieDetail>
}

class MovieDetailRepositoryImpl @Inject constructor(private val service: MovieDetailService) :
    MovieDetailRepository {
    override fun getMovieDetail(id: Int): Single<MovieDetail> {
        return service.getMovieDetail(id)
    }

}