package br.com.messore.tech.pokedex.domain.repository

import br.com.messore.tech.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(page: Int, pageSize: Int): List<Pokemon>
}
