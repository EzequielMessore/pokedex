package br.com.messore.tech.pokedex.pokelist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.messore.tech.pokedex.domain.model.Pokemon
import br.com.messore.tech.pokedex.domain.usecase.ListPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val listPokemonsUseCase: ListPokemonsUseCase
) : ViewModel() {

    init {
        getPokemons()
    }

    private val _state = MutableStateFlow(listOf<Pokemon>())
    val state: StateFlow<List<Pokemon>> = _state

    private val _action = MutableSharedFlow<String>()
    val action: SharedFlow<String> = _action

    private fun getPokemons() = viewModelScope.launch {
        runCatching {
            withContext(Dispatchers.IO) {
                listPokemonsUseCase()
            }
        }.onSuccess {
            println(it)
            _state.value = it
        }.onFailure {
            it.printStackTrace()
        }
    }
}
