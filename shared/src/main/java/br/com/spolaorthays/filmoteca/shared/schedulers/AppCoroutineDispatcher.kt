package br.com.spolaorthays.filmoteca.shared.schedulers

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutineDispatcher(
    val mainScheduler: CoroutineDispatcher,
    val ioScheduler: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
)