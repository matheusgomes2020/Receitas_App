package com.matheus.receitasapp.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.matheus.receitasapp.navigation.NavDestinations.Main.MAIN
import com.matheus.receitasapp.navigation.graphs.mainNavGraph
import com.matheus.receitasapp.navigation.graphs.recipeDetailsNavGraph2
import com.matheus.receitasapp.navigation.graphs.settingsNavGraph
import com.matheus.receitasapp.navigation.utils.Screen
import com.matheus.receitasapp.utils.BottomNavBar

@Composable
fun MainScreen() {
    var bottomBarVisible by rememberSaveable {
        mutableStateOf(false)
    }

   // val settingsViewModel: SettingsViewModel = hiltViewModel()
//    val isSystemInDarkTheme by settingsViewModel.isDarkModeEnabled.collectAsStateWithLifecycle(
//        initialValue = false
//    )
    //val useDarkIcons = !isSystemInDarkTheme

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    bottomBarVisible = when (navBackStackEntry?.destination?.route) {
        Screen.Home.route -> true
        Screen.Ingredients.route -> true
        Screen.Favorites.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                visible = bottomBarVisible,
                //isSystemInDarkMode = isSystemInDarkTheme
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = MAIN,
            modifier = Modifier.padding(paddingValues)

        ) {

            mainNavGraph(navController, false)
            recipeDetailsNavGraph2(navController = navController)
            //settingsNavGraph(navController)
//            topCollectionsNavGraph(navController)
//            topAuthorNavGraph(navController)
//            discoverNavGraph(navController)
//            findFriendsNavGraph(navController)
//            authMainNavGraph(navController)
//            settingsNavGraph(navController, isSystemInDarkTheme)
//            collectionDetailsNavGraph(navController, isSystemInDarkTheme)

        }
    }

}