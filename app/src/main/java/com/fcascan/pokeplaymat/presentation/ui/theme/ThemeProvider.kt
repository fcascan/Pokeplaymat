package com.fcascan.pokeplaymat.presentation.ui.theme

import androidx.compose.runtime.Composable
import com.fcascan.pokeplaymat.presentation.ui.theme.forest.ForestTheme
import com.fcascan.pokeplaymat.presentation.ui.theme.river.RiverTheme
import com.fcascan.pokeplaymat.presentation.ui.theme.sea.SeaTheme
import com.fcascan.pokeplaymat.presentation.ui.theme.stadium.StadiumTheme
import com.fcascan.pokeplaymat.presentation.ui.theme.town.TownTheme

@Composable
fun ThemeProvider(
    darkTheme: Boolean,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    selectedTheme: String?,
    content: @Composable () -> Unit
) {
    when (CustomTheme.valueOf(selectedTheme)) {
        CustomTheme.Forest -> ForestTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
        ) {
            content()
        }
        CustomTheme.River -> RiverTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
        ) {
            content()
        }
        CustomTheme.Sea -> SeaTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
        ) {
            content()
        }
        CustomTheme.Stadium -> StadiumTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
        ) {
            content()
        }
        CustomTheme.Town -> TownTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor,
        ) {
            content()
        }
    }
}

enum class CustomTheme {
    Forest,
    River,
    Sea,
    Stadium,
    Town;

    companion object {
        fun valueOf(value: String?): CustomTheme {
            return when (value) {
                "Forest" -> Forest
                "River" -> River
                "Sea" -> Sea
                "Stadium" -> Stadium
                "Town" -> Town
                else -> River
            }
        }
    }
}
