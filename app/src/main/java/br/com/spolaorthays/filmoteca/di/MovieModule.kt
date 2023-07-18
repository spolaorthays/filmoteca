package br.com.spolaorthays.filmoteca.di

import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.MainActivity
import br.com.spolaorthays.filmoteca.data.MovieRepository
import br.com.spolaorthays.filmoteca.data.MovieRepositoryImpl
import br.com.spolaorthays.filmoteca.data.MovieService
import br.com.spolaorthays.filmoteca.di.viewModelQualifier.ViewModelKey
import br.com.spolaorthays.filmoteca.presentation.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [MovieContributesModule::class, MovieViewModelModule::class])
abstract class MovieModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}

@Module
abstract class MovieViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}

@Module
abstract class MovieContributesModule {
//    @Binds
//    abstract fun bindsMovieInteractor(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindsMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}