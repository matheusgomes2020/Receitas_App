package com.matheus.receitasapp.presentation.recipe_detail

import com.matheus.receitasapp.data.remote.dto.Digest
import com.matheus.receitasapp.data.remote.dto.Ingredient

data class UiStateRecipeDetail(
//sealed class UiState {
    //object Loading : UiState()
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    //data class Content(
    val label: String? = null,
    val id: String? = null,
    val image: String? = null,
    val totalTime: String? = null,
    val calories: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
    //val instructionLines: List<String> = emptyList(),
    val digest: List<Digest> = emptyList(),
    val carbohydrates: String = "Not informed",
    val fat: String = "Not informed",
    val sodium: String = "Not informed",
    var protein: String = "Not informed",
    var fiber: String = "Not informed",
    var sugar: String = "Not informed",
    var cholesterol: String = "Not informed",
    var saturated: String = "Not informed",
    var trans: String = "Not informed",
    var polyunsaturated: String = "Not informed",
    var monounsaturated: String = "Not informed",
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