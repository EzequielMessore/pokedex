package br.com.messore.tech.pokedex.pokelist.viewmodel

import br.com.messore.tech.pokedex.pokelist.model.Pokemon

data class PokemonUiState(
    val idle: Boolean = false,
    val paging: Boolean = false,
    val loading: Boolean = false,
    val canPaginate: Boolean = true,
    val pokemons: List<Pokemon> = emptyList(),
)

sealed interface PokemonUiAction
