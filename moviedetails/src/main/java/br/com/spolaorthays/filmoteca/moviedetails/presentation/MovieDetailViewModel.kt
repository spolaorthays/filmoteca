package br.com.spolaorthays.filmoteca.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarData
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractor
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.MovieDetailState
import br.com.spolaorthays.filmoteca.moviedetails.domain.states.QuotationState
import br.com.spolaorthays.filmoteca.shared.schedulers.AppCoroutineDispatcher
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val interactor: MovieDetailInteractor,
) : BaseViewModel() {

    val movieDetailState = MutableLiveData<MovieDetailState>()
    val quotationState = MutableLiveData<QuotationState>()

    fun getDetails(id: Int) {
        viewModelScope.launch {
            try {
                movieDetailState.value = MovieDetailState.Loading

                val result = interactor.getMovieDetail(id)

                movieDetailState.value = result
            } catch (e: Exception) {
                movieDetailState.value = MovieDetailState.Error(e.message.toString())
            }
        }
    }

    fun getQuotation(budget: Long) {
        viewModelScope.launch {
            try {
                quotationState.value = QuotationState.Loading

                val result = interactor.getDollarQuotation(budget)
                quotationState.value = result
            } catch (e: Exception) {
                quotationState.value = QuotationState.Error(e.message.toString())
            }
        }
    }

    fun calculateBudgetToReal(budget: Long, dollarData: DollarData) {
        val value = interactor.calculatedRealValue(
            budget = budget,
            quotation = dollarData.purchaseQuotation ?: ""
        )
        quotationState.value = QuotationState.ConvertedQuotation(budgetBrazil = value)
    }

}