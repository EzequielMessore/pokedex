package br.com.messore.tech.pokedex.network.extensions

import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addInterceptors(interceptors: Iterable<Interceptor>) = apply {
    interceptors.forEach(::addInterceptor)
}
