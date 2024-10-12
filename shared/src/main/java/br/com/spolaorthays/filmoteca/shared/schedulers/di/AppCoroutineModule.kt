package br.com.spolaorthays.filmoteca.shared.schedulers.di

import br.com.spolaorthays.filmoteca.shared.schedulers.AppCoroutineDispatcher
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.ComputationSchedulerQualifier
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.IoSchedulerQualifier
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.MainSchedulerQualifier
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppCoroutineModule {

    @Singleton
    @Provides
    fun provideSchedulers(
        @MainSchedulerQualifier mainScheduler: CoroutineDispatcher,
        @IoSchedulerQualifier ioScheduler: CoroutineDispatcher,
        @ComputationSchedulerQualifier computationScheduler: CoroutineDispatcher
    ) = AppCoroutineDispatcher(
        mainScheduler = mainScheduler,
        ioScheduler = ioScheduler,
        computation = computationScheduler
    )

    @MainSchedulerQualifier
    @Provides
    fun provideMainThread(): CoroutineDispatcher = Dispatchers.Main

    @IoSchedulerQualifier
    @Provides
    fun provideIoThread(): CoroutineDispatcher = Dispatchers.IO

    @ComputationSchedulerQualifier
    @Provides
    fun provideComputationThread(): CoroutineDispatcher = Dispatchers.Default
}