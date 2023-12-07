package com.matheus.receitasapp.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.common.CustomPadding
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.favorites.components.RecipeFavoritesCard
import com.matheus.receitasapp.presentation.recipes.AddRecipeViewModel
import com.matheus.receitasapp.presentation.recipes.RecipesEvent
import com.matheus.receitasapp.ui.theme.DarkGrey11
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(navController: NavController, isSystemInDarkTheme: Boolean,
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
            MainAppBar(icon1 = R.drawable.adjustments, title = "Favorites" , imageUrl = "",
                onSearchClick = {

                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Dp20
            ) {
                SubtitleHeader(title = "Receitas", isIconVisible = true, isSystemInDarkTheme = useDarkIcons )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
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
                            )}
                    } )
                }
            }
        }
    }
}
    
    
