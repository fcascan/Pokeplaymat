package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Shadow
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme
import com.fcascan.pokeplaymat.utils.VibrationHelper

@Composable
fun LogoButton(
    size: DpSize = DpSize(Size.Huge, Size.Huge),
    padding: Dp = Padding.None,
    painter: Painter,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(Size.Huge)
            .clickable(
                onClick = {
                VibrationHelper().triggerVibration(context)
                onClick()
            },)
    ) {
        Image(
            painter = painter,
            contentDescription = painter.toString(),
            modifier = Modifier
//                .fillMaxSize()
                .align(Alignment.Center)
                .size(size)
                .padding(padding)
                .shadow(Shadow.Medium, shape = MaterialTheme.shapes.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LogoButtonPreview() {
    StadiumTheme {
        LogoButton(
            painter = painterResource(id = R.drawable.btn_coin),
            onClick = {}
        )
    }
}