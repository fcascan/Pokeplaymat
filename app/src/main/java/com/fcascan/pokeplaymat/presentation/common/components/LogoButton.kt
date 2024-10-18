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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.ui.theme.PokeplaymatTheme
import com.fcascan.pokeplaymat.utils.VibrationHelper

@Composable
fun LogoButton(
    size: DpSize = DpSize(64.dp, 64.dp),
    padding: Dp = 0.dp,
    painter: Painter,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(64.dp, 64.dp)
            .clickable(
                onClick = {
                VibrationHelper().triggerVibration(context)
                onClick()
            },)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
//                .fillMaxSize()
                .align(Alignment.Center)
                .size(size)
                .padding(padding)
                .shadow(18.dp, shape = MaterialTheme.shapes.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoButtonPreview() {
    PokeplaymatTheme {
        LogoButton(
            painter = painterResource(id = R.drawable.btn_coin),
            onClick = {}
        )
    }
}