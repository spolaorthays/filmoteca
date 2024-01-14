package br.com.spolaorthays.filmoteca.movie.di

import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.shared.viewmodel.viewModelQualifier.ViewModelKey
import br.com.spolaorthays.filmoteca.movie.data.MovieRepository
import br.com.spolaorthays.filmoteca.movie.data.MovieRepositoryImpl
import br.com.spolaorthays.filmoteca.movie.data.remote.MovieService
import br.com.spolaorthays.filmoteca.movie.domain.MovieInteractor
import br.com.spolaorthays.filmoteca.movie.domain.MovieInteractorImpl
import br.com.spolaorthays.filmoteca.movie.presentation.MainActivity
import br.com.spolaorthays.filmoteca.movie.presentation.MovieViewModel
import br.com.spolaorthays.filmoteca.shared.network.di.qualifiers.APIMovieDBQualifier
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
    @Binds
    abstract fun bindsMovieInteractor(interactorImpl: MovieInteractorImpl): MovieInteractor

    @Binds
    abstract fun bindsMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    companion object {
        @Provides
        fun provideMovieService(@APIMovieDBQualifier retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}