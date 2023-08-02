package br.com.spolaorthays.filmoteca.shared.schedulers

import io.reactivex.Scheduler

data class AppSchedulers(
    val mainScheduler: Scheduler,
    val ioScheduler: Scheduler,
    val computation: Scheduler
)