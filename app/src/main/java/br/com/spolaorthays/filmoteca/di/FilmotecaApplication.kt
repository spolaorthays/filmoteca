package br.com.spolaorthays.filmoteca.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class FilmotecaApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}