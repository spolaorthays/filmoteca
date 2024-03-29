package br.com.spolaorthays.filmoteca.moviedetails.di

import androidx.lifecycle.ViewModel
import br.com.spolaorthays.filmoteca.moviedetails.data.DollarQuotationService
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepository
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailRepositoryImpl
import br.com.spolaorthays.filmoteca.moviedetails.data.MovieDetailService
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractor
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailInteractorImpl
import br.com.spolaorthays.filmoteca.moviedetails.presentation.DetailActivity
import br.com.spolaorthays.filmoteca.moviedetails.presentation.MovieDetailViewModel
import br.com.spolaorthays.filmoteca.shared.network.di.qualifiers.APIBCBDollarQualifier
import br.com.spolaorthays.filmoteca.shared.network.di.qualifiers.APIMovieDBQualifier
import br.com.spolaorthays.filmoteca.shared.viewmodel.viewModelQualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [MovieDetailViewModelModule::class, MovieDetailsContributes::class])
abstract class MovieDetailsModule {
    @ContributesAndroidInjector
    abstract fun provideDetailActivity(): DetailActivity
}

@Module
abstract class MovieDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel
}

@Module
abstract class MovieDetailsContributes {

    @Binds
    abstract fun bindMovieDetailsRepository(repositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository

    @Binds
    abstract fun bindMovieDetailsInteractor(interactorImpl: MovieDetailInteractorImpl): MovieDetailInteractor

    companion object {
        @Provides
        fun provideMovieDetailService(@APIMovieDBQualifier retrofit: Retrofit): MovieDetailService =
            retrofit.create(MovieDetailService::class.java)

        @Provides
        fun provideDollarQuotationServiceService(@APIBCBDollarQualifier retrofit: Retrofit): DollarQuotationService =
            retrofit.create(DollarQuotationService::class.java)
    }
}