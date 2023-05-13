package br.com.messore.tech.pokedex.pokelist.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.domain.usecase.ListPokemonsUseCase
import br.com.messore.tech.pokedex.pokelist.BaseViewModel
import br.com.messore.tech.pokedex.pokelist.mapper.toModel
import br.com.messore.tech.pokedex.pokelist.model.PokemonType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PAGE_SIZE = 20

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val listPokemonsUseCase: ListPokemonsUseCase
) : BaseViewModel<PokemonUiState, PokemonUiAction>(PokemonUiState()) {

    private var page = 0
    private var isIdle = true

    init {
        getPokemons()
    }

    fun onTypesClicked() {
        sendAction(PokemonUiAction.ShowTypes)
    }

    fun onTypeSelected(type: PokemonType?) {
        setState { copy(selectedType = type) }
        refreshResult()
    }

    fun onSortClicked() {
        sendAction(PokemonUiAction.ShowSort)
    }

    fun onSortSelected(sort: PokemonSort) {
        setState { copy(selectedSort = sort) }
        refreshResult()
    }

    fun getPokemons() = viewModelScope.launch {
        if (isIdle.not()) return@launch
        isIdle = false

        if (page == 0) setState { copy(loading = true) }
        else setState { copy(paging = true) }

        runCatching {
            withContext(Dispatchers.IO) {
                listPokemonsUseCase(page, PAGE_SIZE, stateValue.selectedType?.originalType, stateValue.selectedSort)
            }
        }.onSuccess(::handleSuccess)
            .onFailure(::handleFailure)
        isIdle = true
    }

    private fun refreshResult() {
        page = 0
        setState { copy(pokemons = emptyList()) }
        getPokemons()
    }

    private fun handleSuccess(newList: List<Pokemon>) {
        page++
        setState {
            copy(
                loading = false, paging = false,
                pokemons = pokemons + newList.toModel(), canPaginate = newList.size == PAGE_SIZE
            )
        }
    }

    @SuppressWarnings("UnusedPrivateMember")
    private fun handleFailure(throwable: Throwable) {
        setState {
            copy(loading = false, paging = false)
        }
    }
}
