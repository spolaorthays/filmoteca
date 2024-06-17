package br.com.spolaorthays.filmoteca.moviedetails.domain

import androidx.annotation.VisibleForTesting
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepository
import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarData
import br.com.spolaorthays.filmoteca.shared.extensions.formatReal
import br.com.spolaorthays.filmoteca.shared.extensions.formatterCalendarDate
import br.com.spolaorthays.filmoteca.shared.extensions.genericDateFormatter
import br.com.spolaorthays.filmoteca.shared.model.Constants.DOLLAR_API_REQUEST_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.JSON_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MOVIE_API_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MY_PROJECT_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.NOT_AVAILABLE
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import io.reactivex.Single
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

interface MovieDetailInteractor {
    fun getMovieDetail(id: Int): Single<MovieDetailState>
    fun getDollarQuotation(budget: Long): Single<QuotationState>
    fun calculatedRealValue(budget: Long, quotation: String): String
}

class MovieDetailInteractorImpl @Inject constructor(private val repository: MovieDetailRepository) :
    MovieDetailInteractor {
    override fun getMovieDetail(id: Int): Single<MovieDetailState> {
        return repository.getMovieDetail(id).map { movieDetail ->
            MovieDetailState.SuccessDetails(
                details = movieDetail.copy(
                    releaseDate = genericDateFormatter(
                        date = movieDetail.releaseDate,
                        originalFormat = MOVIE_API_FORMAT,
                        neededFormat = MY_PROJECT_FORMAT
                    )
                ),
                budget = movieDetail.budget
            )
        }
    }

    override fun getDollarQuotation(budget: Long): Single<QuotationState> {
        val datesQuotation = getDatesToQuotation()
        return repository.getDollarQuotation(
            initialData = datesQuotation.first,
            finalData = datesQuotation.second,
            format = JSON_FORMAT
        ).map {
            QuotationState.Success(dollarData = it.periodQuotation.last(), budget = budget)
        }
    }

    override fun calculatedRealValue(budget: Long, quotation: String): String {
        val realValue = budget.toDouble() * quotation.toDouble()
        return try {
            formatReal(realValue)
        } catch (e: Exception) {
            NOT_AVAILABLE
        }
    }

    @VisibleForTesting
    fun getActualDate(): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        return genericDateFormatter(
            date = formatterCalendarDate(calendar.time),
            originalFormat = MOVIE_API_FORMAT,
            neededFormat = DOLLAR_API_REQUEST_FORMAT
        )
    }

    @VisibleForTesting
    fun getDateOneMonthAgo(): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.MONTH, -1)
        return genericDateFormatter(
            date = formatterCalendarDate(calendar.time),
            originalFormat = MOVIE_API_FORMAT,
            neededFormat = DOLLAR_API_REQUEST_FORMAT
        )
    }

    /**
     * This API need single quotes in date values to work
     **/
    @VisibleForTesting
    fun getDatesToQuotation(): Pair<String, String> {
        val oneMonthAgo = getDateOneMonthAgo()
        val actualDate = getActualDate()
        return Pair("\'${oneMonthAgo}\'", "\'${actualDate}\'")
    }
}