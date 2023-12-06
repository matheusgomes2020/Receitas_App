package com.matheus.receitasapp.presentation.recipes

import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.util.RecipeOrder


sealed class RecipesEvent{
    data class Order(val recipeOrder: RecipeOrder): RecipesEvent()
    data class DeleteRecipe(val recipeRoom: RecipeRoom): RecipesEvent()

   // data class AddRecipe(val recipeRoom: RecipeRoom): RecipesEvent()
    object RestoreRecipe: RecipesEvent()
    object ToggleOrderSection: RecipesEvent()
}