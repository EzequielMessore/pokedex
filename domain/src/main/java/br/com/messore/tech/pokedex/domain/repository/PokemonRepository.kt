package br.com.messore.tech.pokedex.domain.repository

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.model.PokemonType

interface PokemonRepository {
    suspend fun getPokemons(
        page: Int,
        term: String?,
        pageSize: Int,
        type: PokemonType?,
        sort: PokemonSort?,
    ): List<Pokemon>
}
