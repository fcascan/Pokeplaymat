package com.fcascan.pokeplaymat.presentation.navigation

import MainScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fcascan.pokeplaymat.presentation.ui.view.SettingsScreen
import com.fcascan.pokeplaymat.presentation.ui.view.GuideScreen

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = Main,
        ) {
            composable<Main> {
                MainScreen(
                    navigateToSettings = {
                        navController.navigate(Settings)
                    },
                    navigateToGuide = {
                        navController.navigate(Guide)
                    }
                )
            }
            composable<Settings> {
                SettingsScreen(
                    closeApp = {
                        navController.popBackStack()
                    }
                )
            }
            composable<Guide> {
                GuideScreen(
                    navigateToBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
