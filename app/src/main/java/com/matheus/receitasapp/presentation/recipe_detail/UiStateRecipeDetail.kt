package com.matheus.receitasapp.presentation.recipe_detail

import com.matheus.receitasapp.data.remote.dto.Ingredient

data class UiStateRecipeDetail(
//sealed class UiState {
    //object Loading : UiState()
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    //data class Content(
    val label: String? = null,
    val image: String? = null,
    val totalTime: String? = null,
    val calories: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
   // ) : UiState()
//}

//
//data class UiState(
//    val isLoading: Boolean = false,
//    val label: String = "",
//    val image: String = "",
//    val password: String = "",
//)
)