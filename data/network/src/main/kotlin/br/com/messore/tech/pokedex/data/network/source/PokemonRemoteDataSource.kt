package br.com.messore.tech.pokedex.data.network.source

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.data.network.mapper.toDomain
import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.service.PokemonService
import br.com.messore.tech.pokedex.domain.model.Pokemon
import javax.inject.Inject

private const val PAGE_SIZE = 10
private const val OPERATION_NAME = "pokemonsWithTypes"

class PokemonRemoteDataSource @Inject constructor(
    private val service: PokemonService
) : PokemonDataSource.Remote {

    override suspend fun getPokemons(page: Int): List<Pokemon> {
        val request = GraphQLRequest(
            query = buildGraphQLQuery(PAGE_SIZE, PAGE_SIZE * page),
            operationName = OPERATION_NAME,
        )
        // todo check with @pedrox-hs
        return service.post("https://beta.pokeapi.co/graphql/v1beta", request).toDomain()
    }

    private fun buildGraphQLQuery(limit: Int, offset: Int) =
        """
            query $OPERATION_NAME {pokemon: pokemon_v2_pokemon(limit: $limit, offset: $offset) 
            {id,name,types: pokemon_v2_pokemontypes(order_by: {slot: asc}) {type: pokemon_v2_type {id,name}}}}
        """.trimIndent()
}
