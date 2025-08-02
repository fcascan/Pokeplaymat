package com.fcascan.pokeplaymat

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
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fcascan.pokeplaymat.presentation.navigation.NavigationWrapper
import com.fcascan.pokeplaymat.presentation.ui.theme.ThemeProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkTheme by mainActivityViewModel.isDarkTheme.collectAsStateWithLifecycle()
            val isHorizontal by mainActivityViewModel.isHorizontal.collectAsStateWithLifecycle()
            val selectedTheme by mainActivityViewModel.selectedTheme.collectAsStateWithLifecycle()

            Log.d(TAG, "onCreate: isDarkTheme=$isDarkTheme, isHorizontal=$isHorizontal, selectedTheme=$selectedTheme")

            if (isDarkTheme == null) {
                mainActivityViewModel.setIsDarkTheme(isSystemInDarkTheme())
            } else {
                ThemeProvider(
                    darkTheme = isDarkTheme?: isSystemInDarkTheme(),
                    selectedTheme = selectedTheme,
                ) {
                    NavigationWrapper()
                }
            }

            //Screen orientation:
            requestedOrientation = if (isHorizontal) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        //Edge-to-edge
        enableEdgeToEdge()

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
