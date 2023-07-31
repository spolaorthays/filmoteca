package br.com.spolaorthays.movie.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.shared.model.Movie
import br.com.spolaorthays.movie.domain.MovieInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val movies = mutableListOf<List<Movie>>()
    val allMovies = MutableLiveData<MutableList<List<Movie>>>()
    val endpointList = MutableLiveData<List<String>>()
    val sessionList = MutableLiveData<List<String>>()

    fun getMovieSessions(url: String) {
        compositeDisposable.add(
            interactor.getMovies(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        movies.add(it)
                        allMovies.postValue(movies)
                    }, onError = {
                        it.message
                    })
        )
    }

    fun getEndpointSession() {
        endpointList.value = interactor.getEndpointList()
        sessionList.value = interactor.getSessionList()
    }
}