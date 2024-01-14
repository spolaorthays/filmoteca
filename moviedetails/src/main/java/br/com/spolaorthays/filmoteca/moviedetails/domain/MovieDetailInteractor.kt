package br.com.spolaorthays.filmoteca.moviedetails.domain

import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepository
import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarPeriodQuotation
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

interface MovieDetailInteractor {
    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): Single<DollarPeriodQuotation>
}

class MovieDetailInteractorImpl @Inject constructor(private val repository: MovieDetailRepository) :
    MovieDetailInteractor {
    override fun getMovieDetail(id: Int): Single<MovieDetail> {
        return repository.getMovieDetail(id)
    }

    override fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): Single<DollarPeriodQuotation> =
         repository.getDollarQuotation(initialData, finalData, format)

}