package br.com.spolaorthays.movie.di

import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.shared.viewmodel.viewModelQualifier.ViewModelKey
import br.com.spolaorthays.movie.data.MovieRepository
import br.com.spolaorthays.movie.data.MovieRepositoryImpl
import br.com.spolaorthays.movie.data.remote.MovieService
import br.com.spolaorthays.movie.domain.MovieInteractor
import br.com.spolaorthays.movie.domain.MovieInteractorImpl
import br.com.spolaorthays.movie.presentation.MainActivity
import br.com.spolaorthays.movie.presentation.MovieViewModel
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
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}