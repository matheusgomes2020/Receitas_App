package com.matheus.receitasapp.domain.use_case.room

import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.repository.RoomRepository

class GetRecipe(
    private val repository: RoomRepository
) {
    suspend operator fun invoke( id: String ): RecipeRoom? {

        return repository.getRecipeById(id)

    }
}