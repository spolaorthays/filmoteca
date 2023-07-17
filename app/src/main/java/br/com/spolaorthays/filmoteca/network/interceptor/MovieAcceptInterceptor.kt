package br.com.spolaorthays.filmoteca.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MovieAcceptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestAccept =
            chain.request().newBuilder().addHeader("accept", "application/json").build()
        return chain.proceed(requestAccept)
    }
}