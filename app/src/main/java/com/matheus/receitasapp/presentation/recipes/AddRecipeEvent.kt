package com.matheus.receitasapp.presentation.recipes



sealed class AddRecipeEvent{
    data class EnteredTitle(val value: String): AddRecipeEvent()
    data class EnteredImage(val value: String): AddRecipeEvent()
    data class EnteredId(val value: String): AddRecipeEvent()
    data class EnteredIngredients(val value: String): AddRecipeEvent()
    data class EnteredTime(val value: String): AddRecipeEvent()
    object SaveRecipe: AddRecipeEvent()
}