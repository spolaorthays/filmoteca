package br.com.spolaorthays.filmoteca.shared.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

//TODO Quais funções seriam interessantes eu trazer como herança na minha viewmodel?
open class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
}