package br.com.spolaorthays.filmoteca.movie.domain.states

import br.com.spolaorthays.filmoteca.shared.model.Movie

sealed class MovieState {
    object Loading : MovieState()
    data class NextSession(val next: Int) : MovieState()

    data class SuccessAllMovieList(
        val completeList: MutableList<List<Movie>>
    ) : MovieState()

    data class Error(val message: String) : MovieState()
}

