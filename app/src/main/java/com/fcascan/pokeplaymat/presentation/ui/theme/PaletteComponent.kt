package com.fcascan.pokeplaymat.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size

@Composable
internal fun ColorGrid(colors: List<Pair<String, Color>>) {
    val rows = colors.chunked(5)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        rows.forEach { rowColors ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowColors.forEach { (name, color) ->
                    ColorBox(name, color)
                }
            }
        }
    }
}

@Composable
private fun ColorBox(name: String, color: Color) {
    val fixedWidth = 250.dp
    Column(
        modifier = Modifier
            .width(fixedWidth)
            .padding(Padding.Tiny)
    ) {
        Box(
            modifier = Modifier
                .height(Size.Enormous)
                .fillMaxWidth()
                .background(color)
        ) {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (color.luminance() > 0.5) Color.Black else Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(Padding.Tiny)
            )
        }
    }
}
