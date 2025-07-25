package com.fcascan.pokeplaymat.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.DrawableRes
import com.fcascan.pokeplaymat.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private fun getSharedPreferences() : SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        getSharedPreferences().registerOnSharedPreferenceChangeListener(listener)
    }

    fun getIsDarkTheme(): Boolean? {
        val sharedPreferences = getSharedPreferences()
        return if (sharedPreferences.contains(IS_DARK_THEME_KEY)) {
            sharedPreferences.getBoolean(IS_DARK_THEME_KEY, false)
        } else {
            null
        }
    }

    fun setIsDarkTheme(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(IS_DARK_THEME_KEY, enabled).apply()
    }

    fun getIsHorizontal(): Boolean = getSharedPreferences().getBoolean(IS_HORIZONTAL_KEY, true)

    fun setIsHorizontal(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(IS_HORIZONTAL_KEY, enabled).apply()
    }

    fun getNumberOfBenchedCards(): Int = getSharedPreferences().getInt(NUMBER_OF_BENCHED_CARDS_KEY, 5)

    fun setNumberOfBenchedCards(number: Int) {
        getSharedPreferences().edit().putInt(NUMBER_OF_BENCHED_CARDS_KEY, number).apply()
    }

    fun getPlayerName(): String? = getSharedPreferences().getString(PLAYER_NAME_KEY, "Player")

    fun setPlayerName(name: String?) {
        getSharedPreferences().edit().putString(PLAYER_NAME_KEY, name).apply()
    }

    @DrawableRes fun getArtwork(): Int = getSharedPreferences().getInt(ARTWORK_KEY, R.drawable.artwork_river)

    fun setArtwork(@DrawableRes artwork: Int) {
        getSharedPreferences().edit().putInt(ARTWORK_KEY, artwork).apply()
    }

    fun getTwoPlayersMode(): Boolean = getSharedPreferences().getBoolean(TWO_PLAYERS_MODE_KEY, false)

    fun setTwoPlayersMode(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(TWO_PLAYERS_MODE_KEY, enabled).apply()
    }

    fun getAlwaysOnScreen(): Boolean = getSharedPreferences().getBoolean(ALWAYS_ON_SCREEN_KEY, false)

    fun setAlwaysOnScreen(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(ALWAYS_ON_SCREEN_KEY, enabled).apply()
    }

    fun getSoundFx(): Boolean = getSharedPreferences().getBoolean(SOUND_FX_KEY, true)

    fun setSoundFx(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(SOUND_FX_KEY, enabled).apply()
    }

    fun getVibration(): Boolean = getSharedPreferences().getBoolean(VIBRATION_KEY, true)

    fun setVibration(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(VIBRATION_KEY, enabled).apply()
    }

    fun getDisplayNotificationBar(): Boolean = getSharedPreferences().getBoolean(DISPLAY_NOTIFICATION_BAR_KEY, true)

    fun setDisplayNotificationBar(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(DISPLAY_NOTIFICATION_BAR_KEY, enabled).apply()
    }

    fun getNotificationSound(): Boolean = getSharedPreferences().getBoolean(NOTIFICATION_SOUND_KEY, true)

    fun setNotificationSound(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(NOTIFICATION_SOUND_KEY, enabled).apply()
    }

    fun getSelectedTheme(): String? = getSharedPreferences().getString(SELECTED_THEME_KEY, null)

    fun setSelectedTheme(theme: String?) {
        getSharedPreferences().edit().putString(SELECTED_THEME_KEY, theme).apply()
    }

    fun getCustomBackground(): Boolean = getSharedPreferences().getBoolean(CUSTOM_BACKGROUND_KEY, false)

    fun setCustomBackground(enabled: Boolean) {
        getSharedPreferences().edit().putBoolean(CUSTOM_BACKGROUND_KEY, enabled).apply()
    }

    fun getThemeColor(): String? = getSharedPreferences().getString(THEME_COLOR_KEY, null)

    fun setThemeColor(color: String?) {
        getSharedPreferences().edit().putString(THEME_COLOR_KEY, color).apply()
    }

    private companion object {
        const val SHARED_PREFERENCES_NAME = "PokePlaymatPreferences"
        const val IS_DARK_THEME_KEY = "isDarkTheme"
        const val IS_HORIZONTAL_KEY = "isHorizontal"
        const val NUMBER_OF_BENCHED_CARDS_KEY = "numberOfBenchedCards"
        const val PLAYER_NAME_KEY = "playerName"
        const val ARTWORK_KEY = "artwork"
        const val TWO_PLAYERS_MODE_KEY = "twoPlayersMode"
        const val ALWAYS_ON_SCREEN_KEY = "alwaysOnScreen"
        const val SOUND_FX_KEY = "soundFx"
        const val VIBRATION_KEY = "vibration"
        const val DISPLAY_NOTIFICATION_BAR_KEY = "displayNotificationBar"
        const val NOTIFICATION_SOUND_KEY = "notificationSound"
        const val SELECTED_THEME_KEY = "selectedTheme"
        const val CUSTOM_BACKGROUND_KEY = "customBackground"
        const val THEME_COLOR_KEY = "themeColor"
    }
}
