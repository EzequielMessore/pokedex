package br.com.messore.tech.pokedex.pokelist

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.messore.tech.pokedex.pokelist.viewmodel.PokemonViewModel

@Composable
fun PokeListRoute(viewModel: PokemonViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            state.canPaginate && (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >=
                (listState.layoutInfo.totalItemsCount - 3)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) viewModel.getPokemons()
    }

    PokeListScreen(
        loading = state.loading,
        paging = state.paging,
        listState = listState,
        pokemonList = state.pokemons,
    )
}
