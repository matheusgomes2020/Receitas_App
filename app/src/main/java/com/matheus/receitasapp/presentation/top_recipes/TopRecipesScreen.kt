package com.matheus.receitasapp.presentation.top_recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.common.AppBarWithBack
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.common.HomeCardShimmer
import com.matheus.receitasapp.common.RecipeShimmerGridItem
import com.matheus.receitasapp.presentation.top_recipes.components.RecipeGridItem
import com.matheus.receitasapp.ui.theme.DarkGrey11

@Composable
fun TopRecipesScreen(
    recipeType: String,
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    viewModel: TopRecipesViewModel =  hiltViewModel()
){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }


    val state = viewModel.state.value
    Scaffold(
        topBar = {
            AppBarWithBack(
                title = recipeType,
                backIcon = Icons.Default.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        )
        {

            state.recipes?.let {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {
                    items(state.recipes) { hit ->
                        RecipeGridItem( navController, recipe = hit.recipe )
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = DpDimensions.Normal, vertical = DpDimensions.Smallest)
                ){
                    items(20) {
                        RecipeShimmerGridItem()
                    }
                }
            }
        }
    }
}

