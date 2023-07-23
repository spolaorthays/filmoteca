package br.com.spolaorthays.filmoteca.di

import android.app.Application
import br.com.spolaorthays.filmoteca.di.splash.SplashModule
import br.com.spolaorthays.filmoteca.shared.network.di.NetworkModule
import br.com.spolaorthays.movie.di.MovieModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        MovieModule::class,
        SplashModule::class
    ]
)
interface AppComponent : AndroidInjector<FilmotecaApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}