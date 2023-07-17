package br.com.spolaorthays.filmoteca.di

import br.com.spolaorthays.filmoteca.data.MovieRepository
import br.com.spolaorthays.filmoteca.data.MovieRepositoryImpl
import br.com.spolaorthays.filmoteca.data.MovieService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [MovieContributesModule::class])
abstract class MovieModule

@Module
abstract class MovieContributesModule {
    @Binds
    abstract fun bindsMovieInteractor(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindsMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}