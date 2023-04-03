package br.com.messore.tech.pokedex.data.data.source

import br.com.messore.tech.pokedex.domain.model.Pokemon

sealed interface PokemonDataSource {
    interface Remote : PokemonDataSource {
        suspend fun getPokemons(page: Int = 0): List<Pokemon>
    }
}
