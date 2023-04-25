package br.com.messore.tech.pokedex.pokelist.viewmodel

import br.com.messore.tech.pokedex.pokelist.model.Pokemon
import br.com.messore.tech.pokedex.pokelist.model.PokemonType

data class PokemonUiState(
    val idle: Boolean = false,
    val paging: Boolean = false,
    val loading: Boolean = false,
    val canPaginate: Boolean = true,
    val selectedType: PokemonType? = null,
    val pokemons: List<Pokemon> = emptyList(),
)

sealed interface PokemonUiAction {
    object ShowOrder: PokemonUiAction
    object ShowTypes : PokemonUiAction
}
