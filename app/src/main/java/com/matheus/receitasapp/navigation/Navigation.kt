package com.matheus.receitasapp.navigation

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.receitasapp.presentation.recipe_detail.RecipeDetailScreen
import com.matheus.receitasapp.presentation.searchRecipes.SearchRecipesScreen

@Composable
fun RootNavigationGraph( navController: NavHostController ) {
    NavHost(navController = navController,
        route = RootGraph.ROOT,
        startDestination = HomeGraph.ROOT
    ) {
        composable(HomeGraph.ROOT) {
         //   SearchRecipesScreen( params =, navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() )
        }
        recipeDetailsNavGraph( navController = navController )
    }
}

fun NavGraphBuilder.recipeDetailsNavGraph( navController: NavController ) {
    navigation(
        route = RecipeDetailsGraph.ROOT,
        startDestination = RecipeDetailsGraph.DETAILS
    ) {
        composable(route = RecipeDetailsGraph.DETAILS + "/{recipeId}",
            arguments = listOf(
                navArgument( "recipeId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("recipeId").let {
                Log.d("TRT", "movieDetailsNavGraph2: ${navBackStackEntry.destination}")
                RecipeDetailScreen(navController = navController)
            }
        }
    }
}