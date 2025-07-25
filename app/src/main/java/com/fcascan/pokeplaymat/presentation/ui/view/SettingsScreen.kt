package com.fcascan.pokeplaymat.presentation.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.common.components.BackGround
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Spacing
import com.fcascan.pokeplaymat.presentation.viewmodel.SettingsScreenState
import com.fcascan.pokeplaymat.presentation.viewmodel.SettingsScreenViewModel

@Composable
fun SettingsScreen(
    closeApp: () -> Unit,
) {
    val viewModel : SettingsScreenViewModel = hiltViewModel()
    val settingsScreenState by viewModel.settingsScreenState.collectAsStateWithLifecycle()

    when(settingsScreenState) {
        is SettingsScreenState.Loading -> {
            //TODO
            // Show loading state if needed
        }
        is SettingsScreenState.Error -> {
            //TODO
            // Handle error state if needed
        }
        is SettingsScreenState.Success -> {
            SettingsScreenContent(
                closeApp = closeApp,
                artwork = (settingsScreenState as SettingsScreenState.Success).artwork,
                twoPlayersMode = (settingsScreenState as SettingsScreenState.Success).twoPlayersMode,
                alwaysOnScreen = (settingsScreenState as SettingsScreenState.Success).alwaysOnScreen,
                soundFx = (settingsScreenState as SettingsScreenState.Success).soundFx,
                vibration = (settingsScreenState as SettingsScreenState.Success).vibration,
                displayNotificationBar = (settingsScreenState as SettingsScreenState.Success).displayNotificationBar,
                selectedTheme = (settingsScreenState as SettingsScreenState.Success).selectedTheme,
                customBackground = (settingsScreenState as SettingsScreenState.Success).customBackground,
                themeColor = (settingsScreenState as SettingsScreenState.Success).themeColor,
                onArtworkChange = { newValue -> viewModel.updateArtwork(newValue) },
                onTwoPlayersModeChange = { newValue -> viewModel.updateTwoPlayersMode(newValue) },
                onAlwaysOnScreenChange = { newValue -> viewModel.updateAlwaysOnScreen(newValue) },
                onSoundFxChange = { newValue -> viewModel.updateSoundFx(newValue) },
                onVibrationChange = { newValue -> viewModel.updateVibration(newValue) },
                onDisplayNotificationBarChange = { newValue -> viewModel.updateDisplayNotificationBar(newValue) },
                onSelectedThemeChange = { newValue -> viewModel.updateSelectedTheme(newValue) },
                onCustomBackgroundChange = { newValue -> viewModel.updateCustomBackground(newValue) },
                onThemeColorChange = { newValue -> viewModel.updateThemeColor(newValue) },
            )
        }
    }
}

@Composable
fun SettingsScreenContent(
    closeApp: () -> Unit,
    @DrawableRes artwork: Int,
    twoPlayersMode: Boolean,
    alwaysOnScreen: Boolean,
    soundFx: Boolean,
    vibration: Boolean,
    displayNotificationBar: Boolean,
    selectedTheme: String?,
    customBackground: Boolean,
    themeColor: String?,
    onArtworkChange: (Int) -> Unit,
    onTwoPlayersModeChange: (Boolean) -> Unit,
    onAlwaysOnScreenChange: (Boolean) -> Unit,
    onSoundFxChange: (Boolean) -> Unit,
    onVibrationChange: (Boolean) -> Unit,
    onDisplayNotificationBarChange: (Boolean) -> Unit,
    onSelectedThemeChange: (String?) -> Unit,
    onCustomBackgroundChange: (Boolean) -> Unit,
    onThemeColorChange: (String?)-> Unit,
) {
    val scrollState = rememberScrollState()

    BackGround(
        artwork,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Padding.Medium,
                bottom = Padding.Medium,
                start = Padding.Medium,
                end = Padding.Massive,
            )
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(Spacing.Small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Two Players Mode")
            Switch(
                checked = twoPlayersMode,
                onCheckedChange = {
                    onTwoPlayersModeChange(it)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Always On Screen")
            Switch(
                checked = alwaysOnScreen,
                onCheckedChange = {
                    onAlwaysOnScreenChange(it)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sound FX")
            Switch(
                checked = soundFx,
                onCheckedChange = {
                    onSoundFxChange(it)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Vibration")
            Switch(
                checked = vibration,
                onCheckedChange = {
                    onVibrationChange(it)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Display Notification Bar")
            Switch(
                checked = displayNotificationBar,
                onCheckedChange = {
                    onDisplayNotificationBarChange(it)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Themes")
            DropdownMenu(
                expanded = false,
                onDismissRequest = { }
            ) {
                DropdownMenuItem(
                    text = { Text("Default") },
                    onClick = { onSelectedThemeChange("Default") },
                )
                DropdownMenuItem(
                    text = { Text("Custom") },
                    onClick = { onSelectedThemeChange("Custom") },
                )
            }
        }

        if (selectedTheme == "Custom") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Upload Background")
                Switch(
                    checked = customBackground,
                    onCheckedChange = { onCustomBackgroundChange(it) }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Theme Color")
                DropdownMenu(
                    expanded = true,
                    onDismissRequest = { }
                ) {
                    DropdownMenuItem(
                        text = { Text("Red") },
                        onClick = { onThemeColorChange("Red") },
                    )
                    DropdownMenuItem(
                        text = { Text("Blue") },
                        onClick = { onThemeColorChange("Blue") },
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { closeApp() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Close App")
        }
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 360)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenContent(
        closeApp = {},
        artwork = R.drawable.artwork_stadium,
        twoPlayersMode = false,
        alwaysOnScreen = false,
        soundFx = true,
        vibration = true,
        displayNotificationBar = true,
        selectedTheme = "Default",
        customBackground = false,
        themeColor = "Default",
        onArtworkChange = {},
        onTwoPlayersModeChange = {},
        onAlwaysOnScreenChange = {},
        onSoundFxChange = {},
        onVibrationChange = {},
        onDisplayNotificationBarChange = {},
        onSelectedThemeChange = {},
        onCustomBackgroundChange = {},
        onThemeColorChange = { _ -> }
    )
}
