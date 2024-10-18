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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcascan.pokeplaymat.presentation.ui.theme.PokeplaymatTheme
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
import com.fcascan.pokeplaymat.R
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
            .size(64.dp)
            .shadow(
                elevation = 16.dp, // Adjust the elevation as needed
                shape = RoundedCornerShape(18.dp),
                clip = false
            ),
        shape = RoundedCornerShape(18.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 28.dp,
            pressedElevation = 28.dp,
            disabledElevation = 28.dp,
            hoveredElevation = 28.dp,
            focusedElevation = 28.dp
        ),
        border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        contentPadding = PaddingValues(0.dp)
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
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .size(64.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
    }
}

@Preview(backgroundColor = 0xFF00FF, showBackground = true)
@Composable
fun SquareButtonPreview() {
    PokeplaymatTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SquareButton(
                icon = Icons.Default.Refresh,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            SquareButton(
                icon = painterResource(id = R.drawable.btn_sp),
                onClick = {}
            )
        }
    }
}

