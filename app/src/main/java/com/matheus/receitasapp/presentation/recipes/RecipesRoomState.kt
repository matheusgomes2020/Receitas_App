package com.matheus.receitasapp.presentation.recipes

import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.util.OrderType
import com.matheus.receitasapp.domain.util.RecipeOrder

data class RecipesRoomState(
    val recipes: List<RecipeRoom> = emptyList(),
    val recipeOrder: RecipeOrder = RecipeOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
