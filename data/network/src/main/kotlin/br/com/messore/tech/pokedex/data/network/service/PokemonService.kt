package br.com.messore.tech.pokedex.data.network.service

import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface PokemonService {

    @POST
    suspend fun post(@Url url: String, @Body body: GraphQLRequest): TestResponse
}

// todo move this and refactor
data class TestResponse(
    val data: Data
)

data class Data(
    val pokemon: List<Pokemon>
)

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<TypeElement>
)

data class TypeElement(
    val type: TypeType
)

data class TypeType(
    val id: Long,
    val name: String
)
