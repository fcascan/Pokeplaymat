package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fcascan.pokeplaymat.presentation.ui.dimen.BorderWidth
import com.fcascan.pokeplaymat.presentation.ui.dimen.Corner
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Shadow
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme
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
        shape = RoundedCornerShape(Corner.LargePlus),
        border = BorderStroke(BorderWidth.Vignette, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .size(width = Size.CustomSize, height = Size.Huge)
            .shadow(Shadow.Medium, RoundedCornerShape(Corner.LargePlus)),
        contentPadding = PaddingValues(Padding.None)
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
private fun RectangularButtonPreview() {
    StadiumTheme {
        RectangularButton(
            text = "Player name",
            onClick = {}
        )
    }
}