package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcascan.pokeplaymat.presentation.ui.theme.PokeplaymatTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.fcascan.pokeplaymat.utils.VibrationHelper

@Composable
fun RectangularButton (
    text: String,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Button(
        onClick = {
            VibrationHelper().triggerVibration(context)
            onClick()
        },
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .size(width = 174.dp, height = 64.dp)
            .shadow(18.dp, RoundedCornerShape(18.dp)),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RectangularButtonPreview() {
    PokeplaymatTheme {
        RectangularButton(
            text = "Player name",
            onClick = {}
        )
    }
}