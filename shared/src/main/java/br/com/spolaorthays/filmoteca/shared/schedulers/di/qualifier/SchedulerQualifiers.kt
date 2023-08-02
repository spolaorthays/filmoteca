package br.com.spolaorthays.filmoteca.shared.schedulers.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoSchedulerQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainSchedulerQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ComputationSchedulerQualifier