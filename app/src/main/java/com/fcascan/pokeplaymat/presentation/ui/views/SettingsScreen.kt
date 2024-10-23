package com.fcascan.pokeplaymat.presentation.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    navigateToBack: () -> Unit,
) {

}

@Preview(showBackground = true, widthDp = 700, heightDp = 360)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        navigateToBack = {}
    )
}