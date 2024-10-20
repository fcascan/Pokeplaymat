package com.fcascan.pokeplaymat.presentation.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fcascan.pokeplaymat.presentation.ui.views.SettingsScreen
import com.fcascan.pokeplaymat.presentation.ui.views.GuideScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
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
                navigateToBack = {
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