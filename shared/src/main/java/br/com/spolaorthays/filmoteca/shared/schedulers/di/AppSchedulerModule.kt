package br.com.spolaorthays.filmoteca.shared.schedulers.di

import br.com.spolaorthays.filmoteca.shared.schedulers.AppSchedulers
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.ComputationSchedulerQualifier
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.IoSchedulerQualifier
import br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier.MainSchedulerQualifier
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppSchedulerModule {

    @Singleton
    @Provides
    fun provideSchedulers(
        @MainSchedulerQualifier mainScheduler: Scheduler,
        @IoSchedulerQualifier ioScheduler: Scheduler,
        @ComputationSchedulerQualifier computationScheduler: Scheduler
    ) = AppSchedulers(
        mainScheduler = mainScheduler,
        ioScheduler = ioScheduler,
        computation = computationScheduler
    )

    @MainSchedulerQualifier
    @Provides
    fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()

    @IoSchedulerQualifier
    @Provides
    fun provideIoThread(): Scheduler = Schedulers.io()

    @ComputationSchedulerQualifier
    @Provides
    fun provideComputationThread(): Scheduler = Schedulers.computation()
}