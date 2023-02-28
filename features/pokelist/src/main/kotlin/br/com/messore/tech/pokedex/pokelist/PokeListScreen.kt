package br.com.messore.tech.pokedex.pokelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@ExperimentalMaterial3Api
fun PokeListScreen() {
    val pokemonTypes = listOf(
        PokemonType(
            text = "Grama",
            color = Color(0xff63bc5a),
            iconResource = R.drawable.ic_type_grass,
        ),
        PokemonType(
            text = "Venenoso",
            color = Color(0xffb567ce),
            iconResource = R.drawable.ic_type_poison,
        ),
    )
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
                text = "Nº001",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0xff333333),
                ),
            )
            Text(
                text = "Bulbasaur",
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                ),
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(pokemonTypes) { pokemonType ->
                    AssistChip(
                        modifier = Modifier.padding(0.dp),
                        onClick = { /* Do something! */ },
                        label = { Text(pokemonType.text, style = TextStyle(fontSize = 11.sp)) },
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(color = Color.White, shape = CircleShape),
                            ) {
                                Icon(
                                    painterResource(pokemonType.iconResource),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)
                                        .align(Alignment.Center),
                                    tint = pokemonType.color,
                                )
                            }
                        },
                        colors = AssistChipDefaults.assistChipColors(containerColor = pokemonType.color),
                        shape = RoundedCornerShape(48.dp),
                        border = null,
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .aspectRatio(1.24f)
                .background(color = Color(0xff63bc5a), shape = RoundedCornerShape(15.dp)),
        ) {
            //
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    PokeListScreen()
}

data class PokemonType(
    val text: String,
    val color: Color,
    val iconResource: Int,
)