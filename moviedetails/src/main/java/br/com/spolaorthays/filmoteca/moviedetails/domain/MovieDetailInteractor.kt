package br.com.spolaorthays.filmoteca.moviedetails.domain

import androidx.annotation.VisibleForTesting
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepository
import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarData
import br.com.spolaorthays.filmoteca.shared.extensions.formatterCalendarDate
import br.com.spolaorthays.filmoteca.shared.extensions.genericDateFormatter
import br.com.spolaorthays.filmoteca.shared.model.Constants.DOLLAR_API_REQUEST_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.JSON_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MOVIE_API_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.Constants.MY_PROJECT_FORMAT
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import io.reactivex.Single
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

interface MovieDetailInteractor {
    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getDollarQuotation(
    ): Single<DollarData>
}

class MovieDetailInteractorImpl @Inject constructor(private val repository: MovieDetailRepository) :
    MovieDetailInteractor {
    override fun getMovieDetail(id: Int): Single<MovieDetail> {
        return repository.getMovieDetail(id).map { movieDetail ->
            movieDetail.copy(
                releaseDate = genericDateFormatter(
                    date = movieDetail.releaseDate,
                    originalFormat = MOVIE_API_FORMAT,
                    neededFormat = MY_PROJECT_FORMAT
                )
            )
        }
    }

    override fun getDollarQuotation(): Single<DollarData> {
        val datesQuotation = getDatesToQuotation()
        return repository.getDollarQuotation(
            initialData = datesQuotation.first,
            finalData = datesQuotation.second,
            format = JSON_FORMAT
        ).map {
            it.periodQuotation.last()
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