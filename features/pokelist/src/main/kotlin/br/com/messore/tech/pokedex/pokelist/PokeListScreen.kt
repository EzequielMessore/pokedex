package br.com.messore.tech.pokedex.pokelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
@ExperimentalMaterial3Api
fun PokeListScreen() {
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
    val pokemonList = listOf(bulbasaur,ivysaur)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(pokemonList) {pokemon->
            PokeItem(pokemon)
        }
    }

}

@Composable
fun PokeItem(pokemon:Pokemon) {
    Row(
        modifier = Modifier
            .aspectRatio(3.22f)
            .background(color = Color(0xffedf6ec), shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Nº${pokemon.id.toString().padStart(3,'0')}",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0xff333333),
                ),
            )
            Text(
                text = pokemon.name,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                ),
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(pokemon.types) { pokemonType ->
                    PokeType(pokemonType)
                }
            }
        }
        Box(
            modifier = Modifier
                .aspectRatio(1.24f)
                .background(color = Color(0xff63bc5a), shape = RoundedCornerShape(15.dp)),
        ) {
            PokeImage(
                pokemon = pokemon,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun PokeType(pokemonType: Pokemon.Type) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .background(
                color = pokemonType.color,
                shape = RoundedCornerShape(15.dp),
            )
            .padding(horizontal = 6.dp, vertical = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color = Color.White, shape = CircleShape),
        ) {
            Icon(
                painter = painterResource(pokemonType.iconResource),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .align(Alignment.Center),
                tint = pokemonType.color,
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = pokemonType.text, color = Color.Black, fontSize = 11.sp)
    }
}

@Composable
fun PokeImage(pokemon: Pokemon, modifier: Modifier) {
    Image(
        modifier = modifier.fillMaxSize(),
        painter = painterResource(pokemon.mainType.iconResource),
        contentDescription = null,
    )
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(brush = Brush.linearGradient(listOf(Color.Transparent, pokemon.mainType.color))),
    )
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = pokemon.image,
        contentDescription = null,
    )
}

typealias Url = String

data class Pokemon(
    val id: Int,
    val name: String,
    val image: Url,
    val types: List<Type>,
) {
    val mainType: Type = types.first()

    data class Type(
        val text: String,
        val color: Color,
        val iconResource: Int,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    PokeListScreen()
}
