package br.com.messore.tech.pokedex.pokelist.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.messore.tech.pokedex.domain.model.PokemonSort
import br.com.messore.tech.pokedex.pokelist.R

private val buttonColor = Color(0xFF333333)

@Composable
internal fun SortBottomSheet(onSortClicked: (PokemonSort) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.choose_sort),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Type(text = R.string.lower_number, color = buttonColor, onTypeClicked = {
            onSortClicked(PokemonSort.NUMBER_ASC)
        })

        Type(text = R.string.higher_number, color = buttonColor, onTypeClicked = {
            onSortClicked(PokemonSort.NUMBER_DESC)
        })

        Type(text = R.string.lower_name, color = buttonColor, onTypeClicked = {
            onSortClicked(PokemonSort.NAME_ASC)
        })

        Type(text = R.string.higher_name, color = buttonColor, onTypeClicked = {
            onSortClicked(PokemonSort.NAME_DESC)
        })
    }
}

@Preview
@Composable
fun SortBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SortBottomSheet()
    }
}
