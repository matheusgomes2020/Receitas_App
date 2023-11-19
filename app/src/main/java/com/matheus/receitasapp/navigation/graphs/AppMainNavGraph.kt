package com.matheus.receitasapp.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.MAIN_APP
import com.matheus.receitasapp.navigation.utils.Screen
import com.matheus.receitasapp.presentation.main.MainScreen

fun NavGraphBuilder.appMainNavGraph(navController: NavController) {
    navigation(
        route = MAIN_APP,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route,
          //  enterTransition = { slideInVerticallyEnterAnimation() },
           // exitTransition = { slideOutVerticallyEnterAnimation() }
             )
        {
            MainScreen()
        }
    }
}