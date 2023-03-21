package br.com.messore.tech.pokedex.network.service

import br.com.messore.tech.pokedex.network.model.Response
import br.com.messore.tech.pokedex.network.model.Type
import retrofit2.http.GET

interface TypeService {

    @GET("type")
    suspend fun getTypes(): Response<Type>
}
