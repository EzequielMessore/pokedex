package br.com.messore.tech.pokedex.pokelist.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.messore.tech.pokedex.domain.model.PokemonType
import br.com.messore.tech.pokedex.pokelist.R
import br.com.messore.tech.pokedex.pokelist.mapper.toModel
import br.com.messore.tech.pokedex.pokelist.model.PokemonType as PokemonTypeModel

@Composable
internal fun FiltersBottomSheet(
    onTypeClicked: (PokemonTypeModel?) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val context = LocalContext.current
        val pokemonTypes = PokemonType.values()
            .map { it.toModel() }
            .sortedBy { context.getString(it.text) }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = stringResource(R.string.choose_type),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600),
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Type(
                    text = R.string.type_all,
                    color = Color.Black,
                    onTypeClicked = { onTypeClicked(null) }
                )
            }
            items(pokemonTypes) { type ->
                Type(
                    text = type.text,
                    color = type.color,
                    onTypeClicked = { onTypeClicked(type) }
                )
            }
        }
    }
}

@Preview
@Composable
fun FiltersBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FiltersBottomSheet()
    }
}
