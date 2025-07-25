package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.ui.dimen.Blur
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme

@Composable
fun BackGround(
    artwork: Int
) {
    Image(
        painter = painterResource(id = artwork),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .blur(Blur.Small)
    )
}

@Preview(showBackground = true, widthDp = 700, heightDp = 360)
@Composable
private fun BackGroundPreview() {
    StadiumTheme {
        BackGround(R.drawable.artwork_stadium)
    }
}