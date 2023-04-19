package br.com.messore.tech.pokedex.pokelist.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FiltersBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
    ) {
        LazyColumn {
            items(50) {
                ListItem(
                    text = { Text("Filter: $it") },
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                )
            }
        }
    }
}
