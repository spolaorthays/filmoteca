package br.com.spolaorthays.movie.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.spolaorthays.movie.data.model.Movie
import br.com.spolaorthays.movie.domain.MovieInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val movieList = MutableLiveData<List<Movie>>()
    val popularMovieList = MutableLiveData<List<Movie>>()
    val allMovies = MutableLiveData<List<Movie>>()

    fun getAllMovieSession() {
        compositeDisposable.add(
            interactor.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribeBy(
                    onSuccess = {
                        movieList.postValue(it)
                        getPopularMovieSession()
                    }, onError = {
                        it.message
                    })
        )
    }

    private fun getPopularMovieSession() {
        compositeDisposable.add(
            interactor.getPopulars()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribeBy(
                    onSuccess = {
                        popularMovieList.postValue(it)
                    }, onError = {
                        it.message
                    })
        )
    }
}