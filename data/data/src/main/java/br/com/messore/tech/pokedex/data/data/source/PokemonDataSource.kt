package br.com.messore.tech.pokedex.data.data.source

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.model.PokemonType

sealed interface PokemonDataSource {
    interface Remote : PokemonDataSource {
        suspend fun getPokemons(
            page: Int,
            term: String?,
            pageSize: Int,
            type: PokemonType?,
            sort: PokemonSort?,
        ): List<Pokemon>
    }
}
