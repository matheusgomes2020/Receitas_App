package com.matheus.receitasapp.navigation

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
import com.matheus.receitasapp.presentation.top_recipes.TopRecipesScreen

fun NavGraphBuilder.topRecipesNavGraph(navController: NavController) {

    val enterTransitionAnimation = slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )

    val exitTransitionAnimation = slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

    navigation(
        startDestination = NavDestinations.TopRecipes.TOP_RECIPES,
        route = NavDestinations.TopRecipes.TOP_RECIPES_MAIN
    ) {
        composable(
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation },
            route = "${NavDestinations.TopRecipes.TOP_RECIPES}/{recipeType}",
            arguments = listOf(
                navArgument( "recipeType" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("recipeType").let {
                TopRecipesScreen(recipeType = it!!, navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() )
            }
        }
    }
}