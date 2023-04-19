package br.com.messore.tech.pokedex.pokelist.compose

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.messore.tech.pokedex.pokelist.model.Pokemon
import br.com.messore.tech.pokedex.pokelist.model.PokemonType
import coil.compose.AsyncImage

@Composable
fun PokeListScreen(
    paging: Boolean = false,
    loading: Boolean = false,
    onTypesClicked: () -> Unit = {},
    onOrderClicked: () -> Unit = {},
    pokemonList: List<Pokemon> = listOf(),
    listState: LazyListState = rememberLazyListState(),
) {
    ScreenLoading(show = loading)

    Column {
        Search()

        Filters(
            onTypesBottomSheet = onTypesClicked,
            onOrderBottomSheet = onOrderClicked,
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(pokemonList) { pokemon ->
                PokeItem(pokemon)
            }

            item {
                ScreenLoading(show = paging)
            }
        }
    }
}

@Composable
fun ScreenLoading(show: Boolean = false) {
    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Search() {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = text,
        placeholder = { Text(text = "Procurar Pókemon...") },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 6.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        onValueChange = { text = it },
        shape = CircleShape
    )
}

const val DarkGray = 0xff333333

@Composable
fun Filters(
    defaultColor: Color = Color(DarkGray),
    onTypesBottomSheet: () -> Unit,
    onOrderBottomSheet: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = onTypesBottomSheet,
            colors = ButtonDefaults.buttonColors(containerColor = defaultColor),
        ) {
            Text(text = "Todos os tipos")
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }

        Button(
            onClick = onOrderBottomSheet,
            colors = ButtonDefaults.buttonColors(containerColor = defaultColor),
        ) {
            Text(text = "Menor número")
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

@Composable
fun PokeItem(pokemon: Pokemon) {
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
                text = "Nº${pokemon.id.toString().padStart(3, '0')}",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(DarkGray),
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
                .background(color = pokemon.mainType.color, shape = RoundedCornerShape(15.dp)),
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
fun PokeType(pokemonType: PokemonType) {
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
        Text(text = stringResource(id = pokemonType.text), color = Color.Black, fontSize = 11.sp)
    }
}

@Composable
fun PokeImage(pokemon: Pokemon, modifier: Modifier) {
    Image(
        modifier = modifier.size(94.dp),
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

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PokeListScreen(pokemonList = createMock())
    }
}

private fun createMock(): List<Pokemon> {
    val pokemonTypes = listOf(
        PokemonType.Grass,
        PokemonType.Poison,
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
