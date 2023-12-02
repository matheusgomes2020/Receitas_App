package com.matheus.receitasapp.domain.repository

import com.matheus.receitasapp.data.remote.dto.Hit

interface RecipesRepository {

    suspend fun searchRecipes( query: String ): List<Hit>
    suspend fun searchRecipesByDiet( query: String, diet: String ): List<Hit>
    suspend fun searchRecipesByHealth( query: String, health: String ): List<Hit>
    suspend fun searchRecipesByCuisineType( query: String, cuisineType: String ): List<Hit>
    suspend fun searchRecipesByMealType( query: String, typeOfMeal: String ): List<Hit>
    suspend fun searchRecipesByAllSelected( query: String, diet: String, cuisineType: String, typeOfMeal: String, health: String ): List<Hit>
    suspend fun searchRecipesByCuisineTypeAndDiet( query: String, diet: String, cuisineType: String ): List<Hit>
    suspend fun searchRecipesByMealTypeAndDiet( query: String, diet: String, mealType: String ): List<Hit>
    suspend fun searchRecipesByMealTypeAndCuisineType( query: String,cuisineType: String, mealType: String ): List<Hit>

    suspend fun getRecipes( mealType: String ): List<Hit>

    suspend fun getRecipesByCuisineType(cuisineType: String ): List<Hit>


    suspend fun getRecipeInfo( query: String ): Hit

}