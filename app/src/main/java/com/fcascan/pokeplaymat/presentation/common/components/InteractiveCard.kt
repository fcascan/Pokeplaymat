package com.fcascan.pokeplaymat.presentation.common.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fcascan.pokeplaymat.utils.CustomViewConfiguration
import com.fcascan.pokeplaymat.utils.VibrationHelper
import kotlinx.coroutines.launch

@Composable
fun InteractiveCard(
    damageText: Int = 0,
    onTap: () -> Unit,
    onDoubleTap: () -> Unit,
    onLongPress: () -> Unit,
    onPositiveSwipe: () -> Unit,
    onNegativeSwipe: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val shape = RoundedCornerShape(22.dp)
//    val customViewConfiguration = CustomViewConfiguration(LocalDensity.current)

//    CompositionLocalProvider(LocalViewConfiguration provides customViewConfiguration) {
        Box(
            modifier = Modifier
                .size(100.dp, 140.dp)
    //            .shadow(
    //                elevation = 58.dp,
    //                clip = false,
    //                shape = shape
    //            )
                .background(MaterialTheme.colorScheme.background, shape)
                .border(
                    border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(16.dp)
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            VibrationHelper().triggerVibration(context)
                            onTap()
                        },
                        onDoubleTap = {
                            VibrationHelper().triggerVibrationWaveform(context)
                            onDoubleTap()
                        },
                        onLongPress = {
                            VibrationHelper().triggerVibration(context, 100)
                            onLongPress()
                        }
                    )
                }
                .pointerInput(Unit) {
                    var accumulatedHorizontalDrag = 0f
                    var accumulatedVerticalDrag = 0f
                    detectHorizontalDragGestures { change, dragAmount ->
                        accumulatedHorizontalDrag += dragAmount
                        val threshold = 50 // Define the threshold in pixels

                        while (accumulatedHorizontalDrag > threshold) {
                            // Drag right
                            VibrationHelper().triggerVibration(context, 10)
                            scope.launch {
                                onPositiveSwipe()
                            }
                            accumulatedHorizontalDrag -= threshold // Reduce the accumulated drag
                        }
                        while (accumulatedHorizontalDrag < -threshold) {
                            // Drag left
                            VibrationHelper().triggerVibration(context, 10)
                            scope.launch {
                                onNegativeSwipe()
                            }
                            accumulatedHorizontalDrag += threshold // Reduce the accumulated drag
                        }
                    }
                    detectVerticalDragGestures { change, dragAmount ->
                        accumulatedVerticalDrag += dragAmount
                        val threshold = 50
                        while (accumulatedVerticalDrag < -threshold) {
                            // Drag up
                            VibrationHelper().triggerVibration(context)
                            scope.launch {
                                onPositiveSwipe()
                            }
                            accumulatedVerticalDrag += threshold // Reduce the accumulated drag
                        }
                        while (accumulatedVerticalDrag > threshold) {
                            // Drag down
                            VibrationHelper().triggerVibrationWaveform(context)
                            scope.launch {
                                onNegativeSwipe()
                            }
                            accumulatedVerticalDrag -= threshold // Reduce the accumulated drag
                        }
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = damageText.toString(),
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.secondary)
                )
            }
        }
//    }
}

@Preview(showBackground = true)
@Composable
fun InteractiveCardPreview() {
    InteractiveCard(
        onTap = {},
        onDoubleTap = {},
        onLongPress = {},
        onPositiveSwipe = {},
        onNegativeSwipe = {}
    )
}