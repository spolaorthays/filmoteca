package br.com.spolaorthays.filmoteca.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarData
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractor
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.MovieDetailState
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.QuotationState
import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val interactor: MovieDetailInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    val movieDetailState = MutableLiveData<MovieDetailState>()
    val quotationState = MutableLiveData<QuotationState>()

    fun getDetails(id: Int) {
        compositeDisposable += interactor.getMovieDetail(id)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeOn(appSchedulers.ioScheduler)
            .doOnSubscribe {
                movieDetailState.value = MovieDetailState.Loading
            }
            .subscribeBy(
                onSuccess = {
                    movieDetailState.value = it
                }, onError = {
                    movieDetailState.value =
                        it.message?.let { message -> MovieDetailState.Error(message = message) }
                })
    }

    fun getQuotation(budget: Long) {
        compositeDisposable += interactor.getDollarQuotation(budget)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeOn(appSchedulers.ioScheduler)
            .doOnSubscribe {
                quotationState.value = QuotationState.Loading
            }
            .subscribeBy(
                onSuccess = {
                    quotationState.value = it
                }, onError = {
                    quotationState.value =
                        it.message?.let { message -> QuotationState.Error(message = message) }
                })
    }

    fun calculateBudgetToReal(budget: Long, dollarData: DollarData) {
        val value = interactor.calculatedRealValue(
            budget = budget,
            quotation = dollarData.purchaseQuotation ?: ""
        )
        quotationState.value = QuotationState.ConvertedQuotation(budgetBrazil = value)
    }

}