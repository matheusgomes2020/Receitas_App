package com.matheus.receitasapp.domain.use_case.room

import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.repository.RoomRepository

class DeleteRecipe(
    private val repository: RoomRepository
) {
    suspend operator fun invoke( recipeRoom: RecipeRoom ) {

        repository.deleteRecipe( recipeRoom )

    }
}