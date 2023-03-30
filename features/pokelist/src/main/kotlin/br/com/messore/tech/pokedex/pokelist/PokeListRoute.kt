package br.com.messore.tech.pokedex.pokelist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.messore.tech.pokedex.pokelist.viewmodel.PokemonViewModel

@Composable
fun PokeListRoute(viewModel: PokemonViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    PokeListScreen(state.map { pokemon ->
        Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png",
            types = pokemon.types.map { type ->
                Pokemon.Type(
                    text = type.name,
                    color = Color(0xff63bc5a),
                    iconResource = R.drawable.ic_type_grass,
                )
            },
        )
    })
}
