package br.com.spolaorthays.filmoteca.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractor
import br.com.spolaorthays.filmoteca.shared.model.MovieDetail
import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val interactor: MovieDetailInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    val movieDetail = MutableLiveData<MovieDetail?>()

    fun getDetails(id: Int) {
        compositeDisposable += interactor.getMovieDetail(id)
            .subscribeOn(appSchedulers.ioScheduler)
            .observeOn(appSchedulers.mainScheduler)
            .subscribeBy(
                onSuccess = {
                    movieDetail.value = it
                }, onError = {
                    it.message
                })
    }
}