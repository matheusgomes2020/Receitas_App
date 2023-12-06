package com.matheus.receitasapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.matheus.receitasapp.common.ShimmerRecipeDetail
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.home.components.RecipeHomeCard
import com.matheus.receitasapp.presentation.profile.components.RecipeFavoritesCard
import com.matheus.receitasapp.presentation.recipes.AddRecipeViewModel
import com.matheus.receitasapp.presentation.recipes.RecipesEvent
import com.matheus.receitasapp.ui.theme.DarkGrey11
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, isSystemInDarkTheme: Boolean,
                  viewModel: AddRecipeViewModel = hiltViewModel()) {

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
            MainAppBar(icon1 = R.drawable.search, title = "Favorites" , imageUrl = "",
                onSearchClick = {
                    navController.navigate( NavDestinations.SearchRecipes.SEARCH_RECIPES)
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            SubtitleHeader(title = "Receitas", isIconVisible = true, isSystemInDarkTheme = useDarkIcons )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
                modifier = Modifier,
                contentPadding = PaddingValues(
                    horizontal = DpDimensions.Dp20,
                    vertical = DpDimensions.Smallest
                )
            ) {

                items(state.recipes) { recipe ->
                    RecipeFavoritesCard(navController, recipe, deleteMovie = {
                        viewModel.onEvent(RecipesEvent.DeleteRecipe(recipe))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Recipe deleted",
                                actionLabel = "Undo"
                            )}
                    } )
                }
            }
        }
    }
}
    
    
