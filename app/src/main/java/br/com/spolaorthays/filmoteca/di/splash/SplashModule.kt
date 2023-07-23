package br.com.spolaorthays.filmoteca.di.splash

import br.com.spolaorthays.filmoteca.presentation.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashModule {

    @ContributesAndroidInjector
    abstract fun provideSplashScreen(): SplashActivity
}