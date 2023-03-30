package br.com.messore.tech.pokedex.data.network.service

import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.model.PokemonResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PokemonService {

    @POST("https://beta.pokeapi.co/graphql/v1beta")
    suspend fun post(@Body body: GraphQLRequest): PokemonResponse
}
