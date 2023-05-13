package br.com.messore.tech.pokedex.pokelist.viewmodel

import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.pokelist.model.Pokemon
import br.com.messore.tech.pokedex.pokelist.model.PokemonType

data class PokemonUiState(
    val idle: Boolean = false,
    val paging: Boolean = false,
    val loading: Boolean = false,
    val canPaginate: Boolean = true,
    val selectedType: PokemonType? = null,
    val selectedSort: PokemonSort? = null,
    val pokemons: List<Pokemon> = emptyList(),
)

sealed interface PokemonUiAction {
    object ShowSort: PokemonUiAction
    object ShowTypes : PokemonUiAction
}
