package com.fcascan.pokeplaymat.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.pokeplaymat.utils.SharedPreferencesUtil
import com.fcascan.pokeplaymat.utils.restartableStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesUtil,
) : ViewModel() {
    val settingsScreenState = flow {
        emit(SettingsScreenState.Loading)
        runCatching {
            val successState = SettingsScreenState.Success(
                artwork = sharedPreferences.getArtwork(),
                twoPlayersMode = sharedPreferences.getTwoPlayersMode(),
                alwaysOnScreen = sharedPreferences.getAlwaysOnScreen(),
                soundFx = sharedPreferences.getSoundFx(),
                vibration = sharedPreferences.getVibration(),
                displayNotificationBar = sharedPreferences.getDisplayNotificationBar(),
                selectedTheme = sharedPreferences.getSelectedTheme(),
                customBackground = sharedPreferences.getCustomBackground(),
                themeColor = sharedPreferences.getThemeColor()
            )
            emit(successState)
        }.onFailure {
            emit(SettingsScreenState.Error(it))
        }
    }.restartableStateIn(
        scope = viewModelScope,
        initialValue = SettingsScreenState.Loading,
    )

    fun updateArtwork(@DrawableRes artwork: Int) {
        sharedPreferences.setArtwork(artwork)
        settingsScreenState.restart()
    }

    fun updateTwoPlayersMode(enabled: Boolean) {
        sharedPreferences.setTwoPlayersMode(enabled)
        settingsScreenState.restart()
    }
    fun updateAlwaysOnScreen(enabled: Boolean) {
        sharedPreferences.setAlwaysOnScreen(enabled)
        settingsScreenState.restart()
    }
    fun updateSoundFx(enabled: Boolean) {
        sharedPreferences.setSoundFx(enabled)
        settingsScreenState.restart()
    }
    fun updateVibration(enabled: Boolean) {
        sharedPreferences.setVibration(enabled)
        settingsScreenState.restart()
    }
    fun updateDisplayNotificationBar(enabled: Boolean) {
        sharedPreferences.setDisplayNotificationBar(enabled)
        settingsScreenState.restart()
    }
    fun updateSelectedTheme(selectedTheme: String?) {
        sharedPreferences.setSelectedTheme(selectedTheme)
        settingsScreenState.restart()
    }
    fun updateCustomBackground(enabled: Boolean) {
        sharedPreferences.setCustomBackground(enabled)
        settingsScreenState.restart()
    }
    fun updateThemeColor(themeColor: String?) {
        sharedPreferences.setThemeColor(themeColor)
        settingsScreenState.restart()
    }
}

sealed class SettingsScreenState {
    data object Loading : SettingsScreenState()
    data class Error(val error: Throwable) : SettingsScreenState()
    data class Success(
        val artwork: Int,
        val twoPlayersMode: Boolean,
        val alwaysOnScreen: Boolean,
        val soundFx: Boolean,
        val vibration: Boolean,
        val displayNotificationBar: Boolean,
        val selectedTheme: String?,
        val customBackground: Boolean,
        val themeColor: String?,
    ) : SettingsScreenState()
}
