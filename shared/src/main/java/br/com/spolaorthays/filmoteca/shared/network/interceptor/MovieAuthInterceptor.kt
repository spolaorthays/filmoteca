package br.com.spolaorthays.filmoteca.shared.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MovieAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestAuth =
            chain.request().newBuilder().addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NTVmYzY2ODIwYmVkNDgzZDMyMTQ1YWY5Zjg1ZTBlNCIsInN1YiI6IjY0YTQ3NWU4ZGExMGYwMDBlMjI1MTMwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.N6lxFzmElIHkVJfLZ5QNIVBvp71FP-Xz3u_VjumyIdc"
            ).build()
        return chain.proceed(requestAuth)
    }
}