package com.matheus.receitasapp.presentation.ingredients

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.matheus.core.domain.model.Recipe2
import com.matheus.receitasapp.common.ShimmerRecipeDetail
import com.matheus.receitasapp.data.remote.dto.Recipe
import java.lang.reflect.Modifier

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun IngredientsScreen(navController: NavController,
                      viewModel: RecipesViewModel = hiltViewModel()) {

    val recipesPagingItems: LazyPagingItems<Recipe2> = viewModel.recipesState.collectAsLazyPagingItems()
    Column {
        LazyColumn() {
            items(recipesPagingItems.itemCount) { index ->
                Text(
                    text = recipesPagingItems[index]!!.label,
                    color = MaterialTheme.colorScheme.primary
                )
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
    }


    val state= viewModel.recipesState.value
    
    Column {
        Text(text = state.toString())
    }

}

@Composable
fun PageLoader() {

}

@Composable
fun LoadingNextPageItem() {

}

@Composable
fun ErrorMessage( message: String, onClickRetry: () -> Unit) {

}
