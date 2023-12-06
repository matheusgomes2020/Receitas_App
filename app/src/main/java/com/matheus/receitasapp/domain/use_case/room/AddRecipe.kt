package com.matheus.receitasapp.domain.use_case.room

import com.matheus.receitasapp.domain.model.InvalidRecipeException
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.repository.RoomRepository

class AddRecipe(
    private val repository: RoomRepository
) {
    @Throws(InvalidRecipeException::class)
    suspend operator fun invoke( recipeRoom: RecipeRoom ) {
        if(recipeRoom.title.isBlank()) {
            throw InvalidRecipeException("The title of the recipe can't be empty.")
        }
        if(recipeRoom.image.isBlank()) {
            throw InvalidRecipeException("The image of the recipe can't be empty.")
        }
        if(recipeRoom.ingredients.isBlank()) {
            throw InvalidRecipeException("The ingredients of the recipe can't be empty.")
        }
        if(recipeRoom.time.isBlank()) {
            throw InvalidRecipeException("The time of the recipe can't be empty.")
        }
        repository.insertRecipe( recipeRoom )
    }
}
