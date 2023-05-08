package br.com.messore.tech.pokedex.data.network.source

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.data.network.builder.PokemonQueryBuilder
import br.com.messore.tech.pokedex.data.network.mapper.toDomain
import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.service.PokemonService
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonType
import javax.inject.Inject

private const val OPERATION_NAME = "pokemonsWithTypes"

class PokemonRemoteDataSource @Inject constructor(
    private val service: PokemonService
) : PokemonDataSource.Remote {

    override suspend fun getPokemons(page: Int, pageSize: Int, type: PokemonType?): List<Pokemon> {
        val request = GraphQLRequest(
            query = PokemonQueryBuilder(OPERATION_NAME)
                .limit(pageSize)
                .offset(pageSize * page)
                .where(type?.let {
                    "pokemon_v2_pokemontypes: {type_id: {_eq: ${it.id}}}"
                })
                .build(),
            operationName = OPERATION_NAME,
        )
        return service.post(request).toDomain()
    }
}
