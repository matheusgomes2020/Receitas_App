package com.matheus.receitasapp.navigation.graphs

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.Main.MAIN
import com.matheus.receitasapp.navigation.topRecipesNavGraph
import com.matheus.receitasapp.navigation.utils.Screen
import com.matheus.receitasapp.presentation.home.HomeScreen
import com.matheus.receitasapp.presentation.ingredients.IngredientsScreen
import com.matheus.receitasapp.presentation.favorites.FavoritesScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController, isSystemInDarkTheme: Boolean) {

    val enterTransitionAnimation = slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )

    val exitTransitionAnimation = slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

    navigation(
        startDestination = Screen.Home.route,
        route = MAIN
    ) {

        composable(
            route = Screen.Home.route,
            enterTransition = {
                enterTransitionAnimation
            },
            exitTransition = { exitTransitionAnimation }
        ) {
            HomeScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme)
        }

        composable(route = Screen.Ingredients.route,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation }
        ) {
            IngredientsScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme)
        }

        composable(route = Screen.Favorites.route,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation }
        ) {
            FavoritesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme)
        }

        searchScreenNavGraph( navController = navController )
        settingsNavGraph(navController = navController)
        recipeDetailsNavGraph2( navController = navController )
        topRecipesNavGraph( navController = navController )
        filtersNavGraph( navController = navController )


    }

}