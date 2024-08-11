package br.com.spolaorthays.filmoteca.shared.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
}