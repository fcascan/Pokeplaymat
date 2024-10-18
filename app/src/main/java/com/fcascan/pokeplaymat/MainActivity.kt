package com.fcascan.pokeplaymat

import MainScreen
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fcascan.pokeplaymat.presentation.ui.theme.PokeplaymatTheme

class MainActivity : ComponentActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //SharedPreferences:
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val isHorizontal = sharedPreferences.getBoolean("isHorizontal", true)
        val backgroundResourceId = sharedPreferences.getInt("backgroundResourceId", R.drawable.artwork_stadium)
        val numberOfBenchedCards = sharedPreferences.getInt("numberOfBenchedCards", 5)
        val playerName = sharedPreferences.getString("playerName", "Player")

        //Log of the values of the SharedPreferences to ensure they are correct:
        Log.d(TAG, "isHorizontal: $isHorizontal, " +
                "backgroundResourceId: $backgroundResourceId, " +
                "numberOfBenchedCards: $numberOfBenchedCards, " +
                "playerName: $playerName"
        )

        setContent {
            PokeplaymatTheme {
                MainScreen(
                    artwork = backgroundResourceId,
                    numberOfBenchedCards = numberOfBenchedCards,
                    playerName = playerName ?: "Player",
                )
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

@Preview(showBackground = true, widthDp = 700, heightDp = 360)
@Composable
fun MainActivityPreview() {
    PokeplaymatTheme {
        MainScreen(
            artwork = R.drawable.artwork_stadium,
            numberOfBenchedCards = 5,
            playerName = "Player"
        )
    }
}