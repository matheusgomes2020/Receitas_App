package com.matheus.receitasapp.domain.repository

import com.matheus.receitasapp.data.remote.dto.Hit

interface RecipesRepository {

    suspend fun searchRecipes( query: String ): List<Hit>

    suspend fun getRecipes( mealType: String ): List<Hit>

    suspend fun getRecipesByCuisineType(cuisineType: String ): List<Hit>


    suspend fun getRecipeInfo( query: String ): Hit

}