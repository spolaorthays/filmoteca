package br.com.spolaorthays.movie.presentation

import androidx.lifecycle.MutableLiveData
import br.com.spolaorthays.filmoteca.shared.model.Movie
import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import br.com.spolaorthays.movie.domain.MovieInteractor
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val interactor: MovieInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    private val movies = mutableListOf<List<Movie>>()
    val allMovies = MutableLiveData<MutableList<List<Movie>>>()
    val endpointList = MutableLiveData<List<String>>()
    val sessionList = MutableLiveData<List<String>>()

    fun getMovieSessions(url: String) {
        compositeDisposable += interactor.getMovies(url)
            .subscribeOn(appSchedulers.ioScheduler)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeBy(
                onSuccess = {
                    movies.add(it)
                    allMovies.postValue(movies)
                }, onError = {
                    it.message
                })
    }

    fun getEndpointSession() {
        endpointList.value = interactor.getEndpointList()
        sessionList.value = interactor.getSessionList()
    }
}