package br.com.messore.tech.pokedex.domain.repository

import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonType

interface PokemonRepository {
    suspend fun getPokemons(page: Int, pageSize: Int, type: PokemonType?): List<Pokemon>
}
