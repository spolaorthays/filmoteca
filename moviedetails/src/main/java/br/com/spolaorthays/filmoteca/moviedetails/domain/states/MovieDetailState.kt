package br.com.spolaorthays.filmoteca.moviedetails.domain.states

import br.com.spolaorthays.filmoteca.shared.model.MovieDetail

sealed class MovieDetailState {
    data class SuccessDetails(
        val details: MovieDetail,
        val budget: Long,
    ) : MovieDetailState()

    object Loading : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}