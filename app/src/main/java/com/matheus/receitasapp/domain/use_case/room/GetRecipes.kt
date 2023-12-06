package com.matheus.receitasapp.domain.use_case.room

import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.repository.RoomRepository
import com.matheus.receitasapp.domain.util.OrderType
import com.matheus.receitasapp.domain.util.RecipeOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipes(
    private val repository: RoomRepository
) {

    operator fun invoke (
        recipeOrder: RecipeOrder = RecipeOrder.Date( OrderType.Descending )
    ) : Flow<List<RecipeRoom>> {

        return repository.getRecipes().map { recipes ->

            when(recipeOrder.orderType) {
                is OrderType.Ascending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Title -> recipes.sortedBy { it.title.lowercase() }
                        is RecipeOrder.Date -> recipes.sortedBy { it.timestamp }
                        is RecipeOrder.Time -> recipes.sortedBy { it.time }
                    }
                }
                is OrderType.Descending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Title -> recipes.sortedByDescending { it.title.lowercase() }
                        is RecipeOrder.Date -> recipes.sortedByDescending { it.timestamp }
                        is RecipeOrder.Time -> recipes.sortedByDescending { it.time }
                    }
                }
            }
        }
    }
}