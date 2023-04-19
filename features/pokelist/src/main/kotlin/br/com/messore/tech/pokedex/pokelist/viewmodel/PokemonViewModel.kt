package br.com.messore.tech.pokedex.pokelist.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.usecase.ListPokemonsUseCase
import br.com.messore.tech.pokedex.pokelist.BaseViewModel
import br.com.messore.tech.pokedex.pokelist.mapper.toModel
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

    fun onOrderClicked() {
        sendAction(PokemonUiAction.ShowOrder)
    }

    fun getPokemons() = viewModelScope.launch {
        if (isIdle.not()) return@launch
        isIdle = false

        if (page == 0) setState { copy(loading = true) }
        else setState { copy(paging = true) }

        runCatching {
            withContext(Dispatchers.IO) {
                listPokemonsUseCase(page, PAGE_SIZE)
            }
        }.onSuccess(::handleSuccess)
            .onFailure(::handleFailure)
        isIdle = true
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
