package com.matheus.receitasapp.domain.repository

import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.data.remote.dto.Recipe

interface RecipesRepository {

    suspend fun searchRecipes( query: String ): List<Hit>

    suspend fun getRecipes( mealType: String ): List<Hit>

    suspend fun getRecipes2( cuisineType: String ): List<Hit>


    suspend fun getRecipeInfo( query: String ): Hit

}