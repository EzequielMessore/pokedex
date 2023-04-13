package br.com.messore.tech.pokedex.data.network.source

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.data.network.mapper.toDomain
import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.service.PokemonService
import br.com.messore.tech.pokedex.domain.model.Pokemon
import javax.inject.Inject

private const val OPERATION_NAME = "pokemonsWithTypes"

class PokemonRemoteDataSource @Inject constructor(
    private val service: PokemonService
) : PokemonDataSource.Remote {

    override suspend fun getPokemons(page: Int, pageSize: Int): List<Pokemon> {
        val request = GraphQLRequest(
            query = buildGraphQLQuery(pageSize, pageSize * page),
            operationName = OPERATION_NAME,
        )
        return service.post(request).toDomain()
    }

    private fun buildGraphQLQuery(limit: Int, offset: Int) =
        """
            query $OPERATION_NAME {pokemon: pokemon_v2_pokemon(limit: $limit, offset: $offset) 
            {id,name,types: pokemon_v2_pokemontypes(order_by: {slot: asc}) {type: pokemon_v2_type {id,name}}}}
        """.trimIndent()
}
