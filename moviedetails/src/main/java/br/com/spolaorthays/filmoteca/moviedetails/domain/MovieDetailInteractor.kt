package br.com.spolaorthays.filmoteca.moviedetails.domain

import androidx.annotation.VisibleForTesting
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepository
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.MovieDetailState
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.QuotationState
import br.com.spolaorthays.filmoteca.shared.extensions.formatReal
import br.com.spolaorthays.filmoteca.shared.extensions.formatterCalendarDate
import br.com.spolaorthays.filmoteca.shared.extensions.genericDateFormatter
import br.com.spolaorthays.filmoteca.shared.model.Constants.DOLLAR_API_REQUEST_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.JSON_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MOVIE_API_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MY_PROJECT_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.NOT_AVAILABLE
import io.reactivex.Single
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

interface MovieDetailInteractor {
    suspend fun getMovieDetail(id: Int): MovieDetailState
    suspend fun getDollarQuotation(budget: Long): QuotationState
    fun calculatedRealValue(budget: Long, quotation: String): String
}

class MovieDetailInteractorImpl @Inject constructor(private val repository: MovieDetailRepository) :
    MovieDetailInteractor {
    override suspend fun getMovieDetail(id: Int): MovieDetailState {
        val result = repository.getMovieDetail(id)

        return MovieDetailState.SuccessDetails(
            details = result.copy(
                releaseDate = genericDateFormatter(
                    date = result.releaseDate,
                    originalFormat = MOVIE_API_FORMAT,
                    neededFormat = MY_PROJECT_FORMAT
                )
            ), budget = result.budget
        )
    }

    override suspend fun getDollarQuotation(budget: Long): QuotationState {
        val datesQuotation = getDatesToQuotation()
        val result = repository.getDollarQuotation(
            initialData = datesQuotation.first,
            finalData = datesQuotation.second,
            format = JSON_FORMAT
        )
        return QuotationState.Success(dollarData = result.periodQuotation.last(), budget = budget)
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