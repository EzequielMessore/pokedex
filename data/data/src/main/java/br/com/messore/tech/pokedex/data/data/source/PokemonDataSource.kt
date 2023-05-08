package br.com.messore.tech.pokedex.data.data.source

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonType

sealed interface PokemonDataSource {
    interface Remote : PokemonDataSource {
        suspend fun getPokemons(page: Int, pageSize: Int, type: PokemonType?): List<Pokemon>
    }
}
