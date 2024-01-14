package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarPeriodQuotation
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

interface MovieDetailRepository {
    fun getMovieDetail(id: Int): Single<MovieDetail>

    fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): Single<DollarPeriodQuotation>
}

class MovieDetailRepositoryImpl @Inject constructor(
    private val service: MovieDetailService,
    private val dollarService: DollarQuotationService
) :
    MovieDetailRepository {
    override fun getMovieDetail(id: Int): Single<MovieDetail> =
        service.getMovieDetail(id)

    override fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): Single<DollarPeriodQuotation> =
        dollarService.getDollarQuotationByPeriod(initialData, finalData, format)

}