package com.matheus.receitasapp.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.receitasapp.presentation.top_recipes.TopRecipesScreen

fun NavGraphBuilder.topRecipesNavGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.TopRecipes.TOP_RECIPES,
        route = NavDestinations.TopRecipes.TOP_RECIPES_MAIN
    ) {
        composable(
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