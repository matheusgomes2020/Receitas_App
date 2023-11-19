package com.matheus.receitasapp.navigation.graphs

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.navigation.NavDestinations.Main.MAIN
import com.matheus.receitasapp.navigation.recipeDetailsNavGraph
import com.matheus.receitasapp.navigation.topRecipesNavGraph
import com.matheus.receitasapp.navigation.utils.Screen
import com.matheus.receitasapp.presentation.home.HomeScreen
import com.matheus.receitasapp.presentation.ingredients.IngredientsScreen
import com.matheus.receitasapp.presentation.profile.ProfileScreen
import com.matheus.receitasapp.presentation.recipe_detail.RecipeDetailScreen
import com.matheus.receitasapp.presentation.searchRecipes.SearchRecipesScreen

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
//            enterTransition = {
//                enterTransitionAnimation
//            },
//            exitTransition = { exitTransitionAnimation }
        ) {
            HomeScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme)
        }

        composable(route = Screen.Ingredients.route,
            //enterTransition = { enterTransitionAnimation },
            //exitTransition = { exitTransitionAnimation }
        ) {
            IngredientsScreen(navController = navController)
        }

        composable(route = Screen.Profile.route,
            //enterTransition = { enterTransitionAnimation },
            //exitTransition = { exitTransitionAnimation }
        ) {
            ProfileScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme)
        }

        composable(
            route = NavDestinations.SearchRecipes.SEARCH_RECIPES,
        ) {
            SearchRecipesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme )
        }

        recipeDetailsNavGraph2(navController)

        topRecipesNavGraph( navController = navController )

    }

}