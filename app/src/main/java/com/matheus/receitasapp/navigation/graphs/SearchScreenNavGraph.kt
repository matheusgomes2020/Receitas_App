package com.matheus.receitasapp.navigation.graphs

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.Filters.FILTERS
import com.matheus.receitasapp.navigation.NavDestinations.Filters.FILTERS_MAIN
import com.matheus.receitasapp.navigation.NavDestinations.SearchRecipes.SEARCH_RECIPES
import com.matheus.receitasapp.navigation.NavDestinations.SearchRecipes.SEARCH_RECIPES_MAIN
import com.matheus.receitasapp.presentation.filters.FiltersScreen
import com.matheus.receitasapp.presentation.searchRecipes.SearchRecipesScreen

fun NavGraphBuilder.searchScreenNavGraph(navController: NavController) {

    val enterTransitionAnimation = slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )

    val exitTransitionAnimation = slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

    navigation(
        startDestination = SEARCH_RECIPES,
        route = SEARCH_RECIPES_MAIN
    ) {
        composable(
            route = SEARCH_RECIPES,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation },
        ) {
            SearchRecipesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() )
        }
    }
}