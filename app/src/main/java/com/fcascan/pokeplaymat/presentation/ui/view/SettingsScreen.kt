package com.fcascan.pokeplaymat.presentation.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.model.CustomThemeSelection
import com.fcascan.pokeplaymat.model.ThemeSelection
import com.fcascan.pokeplaymat.presentation.common.components.BackGround
import com.fcascan.pokeplaymat.presentation.common.components.OptionRowChip
import com.fcascan.pokeplaymat.presentation.common.components.OptionRowSwitch
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Spacing
import com.fcascan.pokeplaymat.presentation.util.CustomThemeSelectionMapper
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
                customThemeSelected = (settingsScreenState as SettingsScreenState.Success).customTheme,
                twoPlayersMode = (settingsScreenState as SettingsScreenState.Success).twoPlayersMode,
                alwaysOnScreen = (settingsScreenState as SettingsScreenState.Success).alwaysOnScreen,
                soundFx = (settingsScreenState as SettingsScreenState.Success).soundFx,
                vibration = (settingsScreenState as SettingsScreenState.Success).vibration,
                displayNotificationBar = (settingsScreenState as SettingsScreenState.Success).displayNotificationBar,
                selectedTheme = (settingsScreenState as SettingsScreenState.Success).selectedTheme,
                customBackground = (settingsScreenState as SettingsScreenState.Success).customBackground,
                themeColor = (settingsScreenState as SettingsScreenState.Success).themeColor,
                onCustomThemeChange = { newValue -> viewModel.updateArtwork(newValue) },
                onTwoPlayersModeChange = { newValue -> viewModel.updateTwoPlayersMode(newValue) },
                onAlwaysOnScreenChange = { newValue -> viewModel.updateAlwaysOnScreen(newValue) },
                onSoundFxChange = { newValue -> viewModel.updateSoundFx(newValue) },
                onVibrationChange = { newValue -> viewModel.updateVibration(newValue) },
                onDisplayNotificationBarChange = { newValue -> viewModel.updateDisplayNotificationBar(newValue) },
                onSelectedThemeChange = { newValue -> viewModel.updateSelectedTheme(newValue) },
                onCustomBackgroundChange = { newValue -> viewModel.updateCustomBackground(newValue) },
                onColorVariantChange = { newValue -> viewModel.updateThemeColor(newValue) },
            )
        }
    }
}

@Composable
fun SettingsScreenContent(
    closeApp: () -> Unit,
    customThemeSelected: CustomThemeSelection?,
    twoPlayersMode: Boolean,
    alwaysOnScreen: Boolean,
    soundFx: Boolean,
    vibration: Boolean,
    displayNotificationBar: Boolean,
    selectedTheme: String?,
    customBackground: Boolean,
    themeColor: String?,
    onCustomThemeChange: (CustomThemeSelection?) -> Unit,
    onTwoPlayersModeChange: (Boolean) -> Unit,
    onAlwaysOnScreenChange: (Boolean) -> Unit,
    onSoundFxChange: (Boolean) -> Unit,
    onVibrationChange: (Boolean) -> Unit,
    onDisplayNotificationBarChange: (Boolean) -> Unit,
    onSelectedThemeChange: (String?) -> Unit,
    onCustomBackgroundChange: (Boolean) -> Unit,
    onColorVariantChange: (String?) -> Unit,
) {
    val scrollState = rememberScrollState()
    val customThemeSelectionMapper = CustomThemeSelectionMapper

    BackGround(
        customThemeSelectionMapper.mapToDrawableResId(customThemeSelected),
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
        OptionRowSwitch(
            primaryLabel = "Two Players Mode",
            secondaryLabel = "Enable or disable two players mode",
            checked = twoPlayersMode,
            onCheckedChange = {
                onTwoPlayersModeChange(it)
            }
        )
        OptionRowSwitch(
            primaryLabel = "Always On Screen",
            secondaryLabel = "Keep the screen always on",
            checked = alwaysOnScreen,
            onCheckedChange = {
                onAlwaysOnScreenChange(it)
            }
        )
        OptionRowSwitch(
            primaryLabel = "Sound FX",
            secondaryLabel = "Enable or disable sound effects",
            checked = soundFx,
            onCheckedChange = {
                onSoundFxChange(it)
            },
        )
        OptionRowSwitch(
            primaryLabel = "Vibration",
            secondaryLabel = "Enable or disable vibration feedback",
            checked = vibration,
            onCheckedChange = {
                onVibrationChange(it)
            },
        )
        OptionRowSwitch(
            primaryLabel = "Display Notification Bar",
            secondaryLabel = "Enable or disable the notification bar",
            checked = displayNotificationBar,
            onCheckedChange = {
                onDisplayNotificationBarChange(it)
            },
        )
        OptionRowChip(
            labelResId = R.string.select_custom_theme_primary_label,
            secondaryLabelResId = R.string.select_custom_theme_secondary_label,
            selectedOption = customThemeSelected?.name,
            options = CustomThemeSelection.entries.map { it.name },
            onOptionSelected = { newValue ->
                onCustomThemeChange(customThemeSelectionMapper.mapToCustomThemeSelection(newValue))
            }
        )
        OptionRowChip(
            labelResId = R.string.select_color_variant_primary_label,
            secondaryLabelResId = R.string.select_color_variant_secondary_label,
            selectedOption = "System Default",
            options = ThemeSelection.entries.map { it.name },
            onOptionSelected = { newValue ->
                onColorVariantChange(newValue)
            }
        )
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
        customThemeSelected = CustomThemeSelection.STADIUM,
        twoPlayersMode = false,
        alwaysOnScreen = false,
        soundFx = true,
        vibration = true,
        displayNotificationBar = true,
        selectedTheme = "Default",
        customBackground = false,
        themeColor = "Default",
        onCustomThemeChange = {},
        onTwoPlayersModeChange = {},
        onAlwaysOnScreenChange = {},
        onSoundFxChange = {},
        onVibrationChange = {},
        onDisplayNotificationBarChange = {},
        onSelectedThemeChange = {},
        onCustomBackgroundChange = {},
        onColorVariantChange = { _ -> }
    )
}
