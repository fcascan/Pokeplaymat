package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.ui.dimen.BorderWidth
import com.fcascan.pokeplaymat.presentation.ui.dimen.Corner
import com.fcascan.pokeplaymat.presentation.ui.dimen.Elevation
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size
import com.fcascan.pokeplaymat.presentation.ui.dimen.Spacing
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme
import com.fcascan.pokeplaymat.utils.VibrationHelper

@Composable
fun SquareButton(
    icon: ImageVector,
    iconSize: Int = 48,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    ElevatedButton(
        onClick = {
            VibrationHelper().triggerVibration(context)
            onClick()
        },
        modifier = Modifier
            .size(Size.Huge)
            .shadow(
                elevation = Elevation.Small,
                shape = RoundedCornerShape(Corner.LargePlus),
                clip = false
            ),
        shape = RoundedCornerShape(Corner.LargePlus),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = Elevation.Large,
            pressedElevation = Elevation.Large,
            disabledElevation = Elevation.Large,
            hoveredElevation = Elevation.Large,
            focusedElevation = Elevation.Large,
        ),
        border = BorderStroke(BorderWidth.Vignette, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        contentPadding = PaddingValues(Padding.None)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.toString(),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(iconSize.dp)
        )
    }
}

@Composable
fun SquareButton(
    icon: Painter,
    iconSize: Int = 40,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    ElevatedButton(
        onClick = {
            VibrationHelper().triggerVibration(context)
            onClick()
        },
        shape = RoundedCornerShape(Corner.LargePlus),
        border = BorderStroke(BorderWidth.Vignette, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .size(Size.Huge),
        contentPadding = PaddingValues(Padding.None)
    ) {
        Image(
            painter = icon,
            contentDescription = icon.toString(),
            modifier = Modifier.size(iconSize.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
fun SquareButtonPreview() {
    StadiumTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SquareButton(
                icon = Icons.Default.Refresh,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
            SquareButton(
                icon = painterResource(id = R.drawable.btn_sp),
                onClick = {}
            )
        }
    }
}

