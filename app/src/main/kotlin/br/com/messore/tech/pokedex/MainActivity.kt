package br.com.messore.tech.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.messore.tech.pokedex.pokelist.PokeListScreen
import br.com.messore.tech.pokedex.pokelist.Pokemon
import br.com.messore.tech.pokedex.pokelist.R
import br.com.messore.tech.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PokeListScreen(createMock())
                }
            }
        }
    }

    private fun createMock(): List<Pokemon> {
        val pokemonTypes = listOf(
            Pokemon.Type(
                text = "Grama",
                color = Color(0xff63bc5a),
                iconResource = R.drawable.ic_type_grass,
            ),
            Pokemon.Type(
                text = "Venenoso",
                color = Color(0xffb567ce),
                iconResource = R.drawable.ic_type_poison,
            ),
        )
        val bulbasaur = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            pokemonTypes,
        )
        val ivysaur = Pokemon(
            2,
            "Ivysaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            pokemonTypes,
        )
        return listOf(bulbasaur, ivysaur)
    }
}
