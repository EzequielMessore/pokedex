package br.com.messore.tech.pokedex.domain.repository

import br.com.messore.tech.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
}
