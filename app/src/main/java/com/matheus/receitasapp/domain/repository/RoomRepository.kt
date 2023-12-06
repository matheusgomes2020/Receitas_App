package com.matheus.receitasapp.domain.repository

import com.matheus.receitasapp.domain.model.RecipeRoom
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    fun getRecipes(): Flow<List<RecipeRoom>>

    suspend fun getRecipeById( id: String ): RecipeRoom?
    suspend fun insertRecipe( recipeRoom: RecipeRoom )
    suspend fun deleteRecipe( recipeRoom: RecipeRoom )

}