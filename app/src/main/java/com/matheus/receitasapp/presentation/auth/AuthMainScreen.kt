package com.matheus.receitasapp.presentation.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.navigation.graphs.appMainNavGraph
import com.matheus.receitasapp.navigation.graphs.authNavGraph
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme

@Composable
fun AuthMainScreen() {
    var navController = rememberNavController()
    Scaffold { paddingValues ->
        NavHost(
            navController.also { navController = it },
            startDestination = NavDestinations.Auth.AUTH_MAIN,
            modifier = Modifier.padding(paddingValues)
        ) {
            authNavGraph(navController)
//            registrationNavGraph(navController)
            appMainNavGraph(navController)
        }
    }
}

@Preview
@Composable
fun AuthMainScreenPreview() {
    ReceitasAppTheme {
        AuthMainScreen()
    }
}