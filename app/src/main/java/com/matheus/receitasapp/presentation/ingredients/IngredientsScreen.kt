package com.matheus.receitasapp.presentation.ingredients

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.core.domain.model.Recipe2
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.common.CustomPadding
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.home.components.RecipeCard
import com.matheus.receitasapp.presentation.home.components.TopRecipeItem
import com.matheus.receitasapp.presentation.recipe_detail.components.ingredients
import com.matheus.receitasapp.presentation.recipe_detail.components.items
import com.matheus.receitasapp.ui.theme.DarkGrey11

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun IngredientsScreen(navController: NavController,
                      isSystemInDarkTheme: Boolean,
                      viewModel: RecipesViewModel = hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val recipesPagingItems: LazyPagingItems<Recipe2> = viewModel.recipesState.collectAsLazyPagingItems()
    val recipesPagingItems2: LazyPagingItems<Recipe2> = viewModel.recipesState2.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MainAppBar(icon1 = R.drawable.search, title = "" , imageUrl = "",
                onSearchClick = {
                    navController.navigate( NavDestinations.SearchRecipes.SEARCH_RECIPES)
                })
        }
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
                modifier = androidx.compose.ui.Modifier,
                contentPadding = PaddingValues(
                    horizontal = DpDimensions.Normal,
                    vertical = DpDimensions.Smallest
                )
            ) {
                items(ingredients) { ingredient ->
                    TopRecipeItem(navController, ingredient)
                }
            }
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Dp20
            ) {
                SubtitleHeader(
                    title = "Tomato",
                    isIconVisible = false,
                    isSystemInDarkTheme = useDarkIcons
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
                modifier = androidx.compose.ui.Modifier,
                contentPadding = PaddingValues(
                    horizontal = DpDimensions.Dp20,
                    vertical = DpDimensions.Smallest
                )
            ) {
                items(recipesPagingItems.itemCount) { index ->
                    RecipeCard(
                        navController = navController,
                        id = recipesPagingItems[index]!!.uri,
                        image = recipesPagingItems[index]!!.imageUrl,
                        label = recipesPagingItems[index]!!.label,
                        qtd = recipesPagingItems[index]!!.ingredientsQuantity.toInt(),
                        totalTime = recipesPagingItems[index]!!.time
                    ) {

                    }

                }
                recipesPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { PageLoader() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = recipesPagingItems.loadState.refresh as LoadState.Error
                            item {
                                ErrorMessage(
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingNextPageItem() }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = recipesPagingItems.loadState.append as LoadState.Error
                            item {
                                ErrorMessage(
                                    // modifier = Modifier,
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }
                    }
                }
            }

            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Dp20
            ) {
                SubtitleHeader(
                    title = "Milk",
                    isIconVisible = false,
                    isSystemInDarkTheme = useDarkIcons
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Dp20),
                modifier = androidx.compose.ui.Modifier,
                contentPadding = PaddingValues(
                    horizontal = DpDimensions.Dp20,
                    vertical = DpDimensions.Smallest
                )
            ) {
                items(recipesPagingItems2.itemCount) { index ->
                    RecipeCard(
                        navController = navController,
                        id = "r",
                        image = recipesPagingItems2[index]!!.imageUrl,
                        label = recipesPagingItems2[index]!!.label,
                        qtd = recipesPagingItems2[index]!!.ingredientsQuantity.toInt(),
                        totalTime = recipesPagingItems2[index]!!.time
                    ) {

                    }

                }
                recipesPagingItems2.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { PageLoader() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = recipesPagingItems2.loadState.refresh as LoadState.Error
                            item {
                                ErrorMessage(
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingNextPageItem() }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = recipesPagingItems2.loadState.append as LoadState.Error
                            item {
                                ErrorMessage(
                                    // modifier = Modifier,
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }
                    }
                }

            }
        }
    }

//    Column {
//        LazyColumn() {
//            items(recipesPagingItems.itemCount) { index ->
//
//
//                RecipeCard(
//                    navController = navController,
//                    id = "r",
//                    image = recipesPagingItems[index]!!.imageUrl,
//                    label = recipesPagingItems[index]!!.label,
//                    qtd = recipesPagingItems[index]!!.ingredientsQuantity.toInt(),
//                    totalTime = recipesPagingItems[index]!!.time
//                ) {
//
//                }
//
//            }
//            recipesPagingItems.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item { PageLoader() }
//                    }
//
//                    loadState.refresh is LoadState.Error -> {
//                        val error = recipesPagingItems.loadState.refresh as LoadState.Error
//                        item {
//                            ErrorMessage(
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//
//                    loadState.append is LoadState.Loading -> {
//                        item { LoadingNextPageItem() }
//                    }
//
//                    loadState.append is LoadState.Error -> {
//                        val error = recipesPagingItems.loadState.append as LoadState.Error
//                        item {
//                            ErrorMessage(
//                               // modifier = Modifier,
//                                message = error.error.localizedMessage!!,
//                                onClickRetry = { retry() })
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//
//    val state= viewModel.recipesState.value
//
//    Column {
//        Text(text = state.toString())
//    }

}

@Composable
fun PageLoader() {
    Text(text = "PageLoader")
}

@Composable
fun LoadingNextPageItem() {
    Text(text = "LoadingNextPageItem")
}

@Composable
fun ErrorMessage( message: String, onClickRetry: () -> Unit) {
    Text(text = "ErrorMessage")
}
