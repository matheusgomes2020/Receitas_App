package com.matheus.receitasapp.data.repository

import com.matheus.receitasapp.data.data_source.RecipeDao
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow


class RoomRepositoryImpl(
    private val dao: RecipeDao
) : RoomRepository {

    override fun getRecipes(): Flow<List<RecipeRoom>> {
        return dao.getRecipes()
    }

    override suspend fun getRecipeById(id: String): RecipeRoom? {
        return dao.getRecipeById(id)
    }

    override suspend fun insertRecipe( recipeRoom: RecipeRoom ) {
        dao.insertRecipe(recipeRoom)
    }

    override suspend fun deleteRecipe( recipeRoom: RecipeRoom ) {
        dao.deleteRecipe(recipeRoom)
    }
}