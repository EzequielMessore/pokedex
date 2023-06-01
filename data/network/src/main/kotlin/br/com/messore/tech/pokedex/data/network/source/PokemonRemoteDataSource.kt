package br.com.messore.tech.pokedex.data.network.source

import br.com.messore.tech.pokedex.data.data.source.PokemonDataSource
import br.com.messore.tech.pokedex.data.network.builder.PokemonQueryBuilder
import br.com.messore.tech.pokedex.data.network.mapper.toDomain
import br.com.messore.tech.pokedex.data.network.mapper.toRemote
import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.service.PokemonService
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.model.PokemonType
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val service: PokemonService,
) : PokemonDataSource.Remote {

    override suspend fun getPokemons(
        page: Int,
        term: String?,
        pageSize: Int,
        type: PokemonType?,
        sort: PokemonSort?,
    ): List<Pokemon> {
        val request = GraphQLRequest(
            query = PokemonQueryBuilder()
                .limit(pageSize)
                .offset(pageSize * page)
                .where(type?.let {
                    "pokemon_v2_pokemontypes: {type_id: {_eq: ${it.id}}}"
                })
                .sort(sort?.toRemote())
                .build(),
            variables = mapOf(
                "term" to term?.let { "%${it}%" }
            )
        )
        return service.post(request).toDomain()
    }
}
