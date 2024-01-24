package com.matheus.receitasapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.Auth.AUTH
import com.matheus.receitasapp.navigation.NavDestinations.Auth.AUTHENTICATION
import com.matheus.receitasapp.presentation.auth.AuthMainScreen
import com.matheus.receitasapp.utils.slideInVerticallyEnterAnimation
import com.matheus.receitasapp.utils.slideOutVerticallyEnterAnimation

fun NavGraphBuilder.authMainNavGraph(navController: NavController) {
    navigation(
        route = AUTH,
        startDestination = AUTHENTICATION
    ) {
        composable(route = AUTHENTICATION,
            enterTransition = { slideInVerticallyEnterAnimation() },
            exitTransition = { slideOutVerticallyEnterAnimation() }) {
            AuthMainScreen()
        }
    }
}