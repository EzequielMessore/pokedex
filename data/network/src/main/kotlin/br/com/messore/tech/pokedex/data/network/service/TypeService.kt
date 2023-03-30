package br.com.messore.tech.pokedex.data.network.service

import br.com.messore.tech.pokedex.data.network.model.Response
import br.com.messore.tech.pokedex.data.network.model.Type
import retrofit2.http.GET

interface TypeService {

    @GET("type")
    suspend fun getTypes(): Response<Type>
}
