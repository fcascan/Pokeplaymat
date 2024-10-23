package com.fcascan.pokeplaymat

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.fcascan.pokeplaymat.presentation.navigation.NavigationWrapper
import com.fcascan.pokeplaymat.presentation.ui.theme.ThemeProvider

class MainActivity : ComponentActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SharedPreferences:
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getString("isDarkTheme", null)
        val isHorizontal = sharedPreferences.getBoolean("isHorizontal", true)
        val initialTheme = sharedPreferences.getString("selectedTheme", null)
        Log.d(TAG, "isHorizontal: $isHorizontal, isDarkTheme: $isDarkTheme")

        var selectedTheme : String? by mutableStateOf(initialTheme)

        //SharedPreferences listener for dynamic theme change:
        preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == "selectedTheme") {
                selectedTheme = sharedPreferences.getString("selectedTheme", null)
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)

        setContent {
            ThemeProvider(
                darkTheme = isDarkTheme?.toBoolean() ?: isSystemInDarkTheme(),
                selectedTheme = selectedTheme,
            ) {
                NavigationWrapper()
            }
        }

        //Edge-to-edge
        enableEdgeToEdge()

        //Screen orientation:
        requestedOrientation = if (isHorizontal) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        //Fullscreen mode of app:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let {
                it.hide(WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
        }
    }
}