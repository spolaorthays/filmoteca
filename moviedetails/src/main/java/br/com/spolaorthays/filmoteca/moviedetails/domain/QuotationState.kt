package br.com.spolaorthays.filmoteca.moviedetails.domain

import br.com.spolaorthays.filmoteca.moviedetails.data.model.DollarData

sealed class QuotationState {
    data class Success(val dollarData: DollarData, val budget: Long) : QuotationState()
    data class ConvertedQuotation(val budgetBrazil: String) : QuotationState()
    object Loading : QuotationState()
    data class Error(val message: String) : QuotationState()
}
