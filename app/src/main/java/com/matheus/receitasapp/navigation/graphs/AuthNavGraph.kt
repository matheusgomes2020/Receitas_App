package com.matheus.receitasapp.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.onboarding.OnboardingScreen
import com.matheus.receitasapp.utils.slideInVerticallyEnterAnimation
import com.matheus.receitasapp.utils.slideOutVerticallyEnterAnimation

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.Auth.ONBOARDING,
        route = NavDestinations.Auth.AUTH_MAIN
    ) {

        composable(
            route = NavDestinations.Auth.ONBOARDING,
            enterTransition = { slideInVerticallyEnterAnimation() },
            exitTransition = { slideOutVerticallyEnterAnimation() }
        ) {
            OnboardingScreen(navController = navController)
        }

        composable(
            route = NavDestinations.Auth.SIGN_UP,
            enterTransition = { slideInVerticallyEnterAnimation() },
            exitTransition = { slideOutVerticallyEnterAnimation() }
        ) {
            //SignupStepsScreen()
        }


    }
}