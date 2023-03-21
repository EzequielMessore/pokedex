package br.com.messore.tech.pokedex.network.service

import retrofit2.http.GET
import retrofit2.http.Url

interface UrlService {

    @GET
    suspend fun <T> callUrl(@Url url: String): T
}
