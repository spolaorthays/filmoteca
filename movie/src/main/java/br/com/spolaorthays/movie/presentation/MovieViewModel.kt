package br.com.spolaorthays.movie.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.spolaorthays.movie.data.model.Movie
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
    val nowPlayingList = MutableLiveData<List<Movie>>()
    val popularMovieList = MutableLiveData<List<Movie>>()
    val topRatedMovieList = MutableLiveData<List<Movie>>()
    val upcomingMovieList = MutableLiveData<List<Movie>>()
    val allMovies = MutableLiveData<MutableList<List<Movie>>>()

    fun getMovieSessions(url: String, position: Int) {
        compositeDisposable.add(
            interactor.getMovies(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        when(position) {
                            0 -> nowPlayingList.postValue(it)
                            1 -> popularMovieList.postValue(it)
                            2 -> topRatedMovieList.postValue(it)
                            3 -> upcomingMovieList.postValue(it)
                        }
                    }, onError = {
                        it.message
                    })
        )
    }
}