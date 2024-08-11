package br.com.spolaorthays.filmoteca.movie.presentation

import androidx.lifecycle.MutableLiveData
import br.com.spolaorthays.filmoteca.shared.model.Movie
import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import br.com.spolaorthays.filmoteca.movie.domain.MovieInteractor
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.NextSession
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.SuccessAllMovieList
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val interactor: MovieInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    private val movies = mutableListOf<List<Movie>>()
    private var endpointListSize: Int = 0
    val sessionList = MutableLiveData<List<String>>()
    val movieState = MutableLiveData<MovieState>()

    fun getMovieSessions(url: String, position: Int) {
        compositeDisposable += interactor.getMovies(url)
            .subscribeOn(appSchedulers.ioScheduler)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeBy(
                onSuccess = {
                    movies.add(it)
                    getAllMovies(movies, position)
                }, onError = {
                    it.message
                })
    }

    private fun getAllMovies(movieList: MutableList<List<Movie>>, position: Int) {
        if (movieList.size == endpointListSize) {
            movieState.value = SuccessAllMovieList(completeList = movieList)
        } else {
            movieState.value = NextSession(next = position + 1)
        }
    }

    fun getEndpointSession(): List<String> {
        val endpointList = interactor.getEndpointList()
        endpointListSize = endpointList.size
        sessionList.value = interactor.getSessionList()
        movieState.value = MovieState.Loading

        return endpointList
    }
}