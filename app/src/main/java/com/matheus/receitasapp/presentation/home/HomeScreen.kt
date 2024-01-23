package com.matheus.receitasapp.presentation.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.common.HomeCardShimmer
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.navigation.NavDestinations.SearchRecipes.SEARCH_RECIPES
import com.matheus.receitasapp.presentation.common.CustomPadding
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.home.components.RecipeHomeCard
import com.matheus.receitasapp.presentation.home.components.TopRecipeItem
import com.matheus.receitasapp.presentation.recipe_detail.components.items
import com.matheus.receitasapp.presentation.searchRecipes.HitListState
import com.matheus.receitasapp.ui.theme.DarkGrey11

@Composable
fun HomeScreen(navController: NavController,
               isSystemInDarkTheme: Boolean,
               viewModel: HomeViewModel =  hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val stateBreakFast =  viewModel.state.value
    val stateSouthAmericanFood =  viewModel.stateCuisineTyp.value


    Scaffold(
        topBar = {
            MainAppBar(icon1 = R.drawable.search, title = "" , imageUrl = "",
                onSearchClick = {
                    navController.navigate( SEARCH_RECIPES)
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = DpDimensions.Normal, vertical = DpDimensions.Smallest)
                ){
                    items(items) { ingredient ->
                        TopRecipeItem(navController,ingredient)
                    }
                }
                CustomPadding(
                    verticalPadding = 0.dp,
                    horizontalPadding = DpDimensions.Dp20
                ) {
                    SubtitleHeader(title = "Breakfast", isIconVisible = false, isSystemInDarkTheme = useDarkIcons )
                }
            stateBreakFast.recipes?.let {
                RecipeHomeCardCell( navController, stateBreakFast )
            }
            if (stateBreakFast.error.isNotBlank()) {
                Error { viewModel.refreshBreakfast() }
            }
            if (stateBreakFast.isLoading) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = DpDimensions.Normal, vertical = DpDimensions.Smallest)
                ){
                    items(3) {
                        HomeCardShimmer()
                    }
                }
            }
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Dp20
            ) {
                SubtitleHeader(title = "South American Food", isIconVisible = false, isSystemInDarkTheme = useDarkIcons )
            }
            stateSouthAmericanFood.recipes?.let {
                RecipeHomeCardCell( navController, stateSouthAmericanFood )
            }

            if (stateSouthAmericanFood.error.isNotBlank()) {
                Error { viewModel.refreshSouthAmerican() }
            }
            if (stateSouthAmericanFood.isLoading) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = DpDimensions.Normal, vertical = DpDimensions.Smallest)
                ){
                    items(3) {
                        HomeCardShimmer()
                    }
                }
            }

        }
    }
}

@Composable
private fun RecipeHomeCardCell( navController: NavController, state: HitListState ) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
        modifier = Modifier,
        contentPadding = PaddingValues(
            horizontal = DpDimensions.Dp20,
            vertical = DpDimensions.Smallest
        )
    ) {
        items(state.recipes) { recipe ->
            RecipeHomeCard( navController, recipe.recipe )
        }
    }
}

@Preview
@Composable
fun aa () {
    HomeScreen(navController = rememberNavController(), isSystemInDarkTheme = false )
}


@Composable
fun Error(
    onClick: () -> Unit
){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Small),
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)


    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                modifier = Modifier.padding(
                    horizontal = DpDimensions.Small
                )
            ) {
                Text(text = "Error",
                    fontWeight = FontWeight.Bold)
                Text(text = "Ops, something went wrong. please try again")
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onClick()
                    }) {
                    Text(text = "Try Again")
                }
            }
        }

    }
}