package br.com.messore.tech.pokedex.pokelist.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Type(text: Int, color: Color, onTypeClicked: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable(onClick = onTypeClicked)
            .background(color, shape = RoundedCornerShape(49.dp)),
    ) {
        Text(
            modifier = Modifier.align(Center),
            text = stringResource(id = text),
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.W600),
        )
    }
}
