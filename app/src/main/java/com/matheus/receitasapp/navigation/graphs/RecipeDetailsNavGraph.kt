package com.matheus.receitasapp.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.RecipeDetails.RECIPE_DETAILS
import com.matheus.receitasapp.navigation.NavDestinations.RecipeDetails.RECIPE_DETAILS_MAIN
import com.matheus.receitasapp.presentation.recipe_detail.RecipeDetailScreen

fun NavGraphBuilder.recipeDetailsNavGraph2(navController: NavController, isSystemInDarkTheme: Boolean) {
    navigation(
        startDestination = RECIPE_DETAILS,
        route = RECIPE_DETAILS_MAIN
    ) {
        composable(
            route = "$RECIPE_DETAILS/{recipeId}",
            arguments = listOf(
                navArgument( "recipeId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("recipeId").let {
                RecipeDetailScreen(isSystemInDarkTheme = isSystemInDarkTheme, navController = navController)
            }
        }
    }
}