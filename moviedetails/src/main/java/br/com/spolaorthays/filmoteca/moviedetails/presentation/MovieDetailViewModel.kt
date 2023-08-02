package br.com.spolaorthays.filmoteca.moviedetails.presentation

import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractor
import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val interactor: MovieDetailInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {
    fun getDetails(id: Int) {
        compositeDisposable += interactor.getMovieDetail(id)
            .subscribeOn(appSchedulers.ioScheduler)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeBy(
                onSuccess = {
                            it
                }, onError = {
                    it.message
                })
    }
}