package com.matheus.receitasapp.navigation.graphs

import android.window.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.receitasapp.navigation.NavDestinations.Splash.SPLASH
import com.matheus.receitasapp.navigation.NavDestinations.Splash.SPLASH_ROOT
import com.matheus.receitasapp.presentation.splash_screen.SplashScreen
import com.matheus.receitasapp.utils.slideInVerticallyEnterAnimation
import com.matheus.receitasapp.utils.slideOutVerticallyEnterAnimation

fun NavGraphBuilder.splashNavGraph(navController: NavController) {
    navigation(
        startDestination = SPLASH,
        route = SPLASH_ROOT
    ) {

        composable(
            route = SPLASH,
           // enterTransition = { slideInVerticallyEnterAnimation() },
            //exitTransition = { slideOutVerticallyEnterAnimation() }
        ) {
            SplashScreen(navController = navController)
        }



    }
}