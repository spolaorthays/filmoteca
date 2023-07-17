package br.com.spolaorthays.filmoteca.di

import br.com.spolaorthays.filmoteca.data.MovieService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val requestAccept =
                chain.request().newBuilder().addHeader("accept", "application/json").build()
            chain.proceed(requestAccept)
        }
        okHttpClient.addInterceptor { chain ->
            val requestAuth =
                chain.request().newBuilder().addHeader(
                    "Authorization",
                    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NTVmYzY2ODIwYmVkNDgzZDMyMTQ1YWY5Zjg1ZTBlNCIsInN1YiI6IjY0YTQ3NWU4ZGExMGYwMDBlMjI1MTMwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.N6lxFzmElIHkVJfLZ5QNIVBvp71FP-Xz3u_VjumyIdc"
                ).build()
            chain.proceed(requestAuth)
        }
        okHttpClient.addInterceptor(httpLoggingInterceptor).build()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)
}