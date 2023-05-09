package br.com.messore.tech.pokedex.pokelist

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.messore.tech.pokedex.pokelist.compose.FiltersBottomSheet
import br.com.messore.tech.pokedex.pokelist.compose.PokeListScreen
import br.com.messore.tech.pokedex.pokelist.compose.SortBottomSheet
import br.com.messore.tech.pokedex.pokelist.extensions.observe
import br.com.messore.tech.pokedex.pokelist.viewmodel.PokemonUiAction
import br.com.messore.tech.pokedex.pokelist.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

typealias ComposableContent = @Composable ColumnScope.() -> Unit

@Composable
fun PokeListRoute(viewModel: PokemonViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    val listState = rememberLazyListState()
    val bottomSheetContent = remember { mutableStateOf<ComposableContent>({}) }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val shouldStartPaginate = remember {
        derivedStateOf {
            state.canPaginate && (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >=
                (listState.layoutInfo.totalItemsCount - 3)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value) viewModel.getPokemons()
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = bottomSheetContent.value,
    ) {
        PokeListScreen(
            listState = listState,
            paging = state.paging,
            loading = state.loading,
            pokemonList = state.pokemons,
            selectedType = state.selectedType,
            selectedSort = state.selectedSort,
            onTypesClicked = viewModel::onTypesClicked,
            onOrderClicked = viewModel::onSortClicked,
        )
    }

    ObserveActions(
        viewModel = viewModel,
        bottomSheetState = bottomSheetState,
        bottomSheetContent = bottomSheetContent
    )
}

@Composable
private fun ObserveActions(
    viewModel: PokemonViewModel,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: MutableState<ComposableContent>,
) {
    val scope = rememberCoroutineScope()
    viewModel.action.observe(LocalLifecycleOwner.current) { action ->
        when (action) {
            PokemonUiAction.ShowTypes -> {
                scope.launch { bottomSheetState.show() }
                bottomSheetContent.value = {
                    FiltersBottomSheet {
                        scope.launch { bottomSheetState.hide() }
                        viewModel.onTypeSelected(it)
                    }
                }
            }

            PokemonUiAction.ShowSort -> {
                scope.launch { bottomSheetState.show() }
                bottomSheetContent.value = {
                    SortBottomSheet {
                        scope.launch { bottomSheetState.hide() }
                        viewModel.onSortSelected(it)
                    }
                }
            }
        }
    }
}
