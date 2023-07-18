package br.com.spolaorthays.filmoteca.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.data.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val title = MutableLiveData<String>()

    fun getMovieTitle() {
        compositeDisposable.add(
            repository.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribeBy(
                    onSuccess = {
                        title.postValue(it.results[0].movieTitle)
                    }, onError = {
                        it.message
                    })
        )
    }
}