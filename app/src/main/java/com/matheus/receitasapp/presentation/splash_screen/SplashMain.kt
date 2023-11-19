package com.matheus.receitasapp.presentation.splash_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.navigation.graphs.appMainNavGraph
import com.matheus.receitasapp.navigation.graphs.splashNavGraph

@Composable
fun SplashMain() {
    var navController = rememberNavController()

    Scaffold { paddingValues ->
        NavHost(
            navController.also { navController = it },
            startDestination = NavDestinations.Splash.SPLASH_ROOT,
            modifier = Modifier.padding(paddingValues)
        ) {
            splashNavGraph(navController)
            appMainNavGraph(navController)
            //authMainNavGraph(navController)
        }
    }

}