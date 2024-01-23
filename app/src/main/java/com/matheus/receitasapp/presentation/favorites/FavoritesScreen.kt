package com.matheus.receitasapp.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.navigation.AppGraph
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.navigation.NavDestinations.Settings.SETTINGS
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.favorites.components.RecipeFavoritesCard
import com.matheus.receitasapp.presentation.recipes.GetRecipesViewModel
import com.matheus.receitasapp.presentation.recipes.RecipesEvent
import com.matheus.receitasapp.ui.theme.DarkGrey11
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(navController: NavController, isSystemInDarkTheme: Boolean,
                    viewModel: GetRecipesViewModel = hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            MainAppBar(icon1 = R.drawable.adjustments, title = "Favorites" , imageUrl = "",
                onSearchClick = {
                    navController.navigate(NavDestinations.Settings.SETTINGS)
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                //.verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
                modifier = Modifier,
                contentPadding = PaddingValues(
                    horizontal = DpDimensions.Dp20,
                    vertical = DpDimensions.Smallest
                )
            ) {

                items(state.recipes) { recipe ->
                    RecipeFavoritesCard(navController, recipe, deleteRecipe = {
                        viewModel.onEvent(RecipesEvent.DeleteRecipe(recipe))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Recipe deleted",
                                actionLabel = "Undo"
                            )
                            if(result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(RecipesEvent.RestoreRecipe)
                            }}
                    } )
                }
            }
        }
    }
}
    
    
