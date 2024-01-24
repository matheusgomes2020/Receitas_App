package com.matheus.receitasapp.navigation.graphs

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.Settings.SETTINGS
import com.matheus.receitasapp.navigation.NavDestinations.Settings.SETTINGS_MAIN
import com.matheus.receitasapp.presentation.settings.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavController, isSystemInDarkTheme: Any?) {

    val enterTransitionAnimation = slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )

    val exitTransitionAnimation = slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

    navigation(
        route = SETTINGS_MAIN,
        startDestination = SETTINGS
    ) {
        composable(
            route = SETTINGS,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation },
        ) {
            SettingsScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() )
        }
    }
}