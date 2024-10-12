package br.com.spolaorthays.filmoteca.moviedetails.data

import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarPeriodQuotation
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import br.com.spolaorthays.filmoteca.shared.schedulers.AppCoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieDetailRepository {
    suspend fun getMovieDetail(id: Int): MovieDetail

    suspend fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): DollarPeriodQuotation
}

class MovieDetailRepositoryImpl @Inject constructor(
    private val service: MovieDetailService,
    private val dollarService: DollarQuotationService,
    private val dispatcher: AppCoroutineDispatcher,
) :
    MovieDetailRepository {
    override suspend fun getMovieDetail(id: Int): MovieDetail =
        withContext(dispatcher.ioScheduler) {
            service.getMovieDetail(id)
        }

    override suspend fun getDollarQuotation(
        initialData: String,
        finalData: String,
        format: String
    ): DollarPeriodQuotation = withContext(dispatcher.ioScheduler) {
        dollarService.getDollarQuotationByPeriod(initialData, finalData, format)
    }

}