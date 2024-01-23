package com.matheus.receitasapp.presentation.ingredients

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
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
import com.matheus.receitasapp.common.HomeCardShimmer
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.common.CustomPadding
import com.matheus.receitasapp.presentation.common.MainAppBar
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.home.components.RecipeCard
import com.matheus.receitasapp.presentation.home.components.TopRecipeItem
import com.matheus.receitasapp.presentation.recipe_detail.components.ingredients
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

    val recipesPagingItemsTomato: LazyPagingItems<Recipe2> = viewModel.recipesState.collectAsLazyPagingItems()
    val recipesPagingItemsMilk: LazyPagingItems<Recipe2> = viewModel.recipesState2.collectAsLazyPagingItems()

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
                items(recipesPagingItemsTomato.itemCount) { index ->
                    RecipeCard(
                        navController = navController,
                        id = recipesPagingItemsTomato[index]!!.uri,
                        image = recipesPagingItemsTomato[index]!!.imageUrl,
                        label = recipesPagingItemsTomato[index]!!.label,
                        qtd = recipesPagingItemsTomato[index]!!.ingredientsQuantity.toInt(),
                        totalTime = recipesPagingItemsTomato[index]!!.time
                    ) {

                    }

                }
                recipesPagingItemsTomato.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { PageLoader() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = recipesPagingItemsTomato.loadState.refresh as LoadState.Error
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
                            val error = recipesPagingItemsTomato.loadState.append as LoadState.Error
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
                items(recipesPagingItemsMilk.itemCount) { index ->
                    RecipeCard(
                        navController = navController,
                        id = recipesPagingItemsMilk[index]!!.uri,
                        image = recipesPagingItemsMilk[index]!!.imageUrl,
                        label = recipesPagingItemsMilk[index]!!.label,
                        qtd = recipesPagingItemsMilk[index]!!.ingredientsQuantity.toInt(),
                        totalTime = recipesPagingItemsMilk[index]!!.time
                    ) {

                    }

                }
                recipesPagingItemsMilk.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { PageLoader() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = recipesPagingItemsMilk.loadState.refresh as LoadState.Error
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
                            val error = recipesPagingItemsMilk.loadState.append as LoadState.Error
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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
        modifier = Modifier.width(500.dp)
            .height(300.dp),
        contentPadding = PaddingValues(
           // horizontal = DpDimensions.Normal,
            vertical = DpDimensions.Smallest)) {
        items(3) {
            HomeCardShimmer()
        }
    }
}

@Composable
fun LoadingNextPageItem() {
    Text(text = "LoadingNextPageItem")
}

@Composable
fun ErrorMessage( message: String, onClickRetry: () -> Unit) {
    Text(text = "ErrorMessage")
}
