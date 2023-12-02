package com.matheus.receitasapp.data.repository

import android.util.Log
import com.matheus.receitasapp.data.remote.RecipeApi
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val api: RecipeApi
): RecipesRepository {
    override suspend fun searchRecipes(query: String): List<Hit> {
        return api.searchRecipes( query = query ).hits
    }

    override suspend fun searchRecipesByDiet(query: String, diet: String): List<Hit> {
        return api.searchRecipesByDiet(query = query, diet = diet).hits
    }

    override suspend fun searchRecipesByHealth(query: String, health: String): List<Hit> {
        return api.searchRecipesByHealth(query = query, health = health).hits
    }

    override suspend fun searchRecipesByCuisineType(query: String, cuisineType: String): List<Hit> {
        return api.searchRecipesByCuisineType(query = query, cuisineType = cuisineType).hits
    }

    override suspend fun searchRecipesByMealType(query: String, typeOfMeal: String): List<Hit> {
        return api.searchRecipesByMealType(query = query, mealType = typeOfMeal).hits
    }

    override suspend fun searchRecipesByAllSelected(
        query: String,
        diet: String,
        cuisineType: String,
        typeOfMeal: String,
        health: String
    ): List<Hit> {
        return api.searchRecipesByAllSelected(query = query, diet = diet, cuisineType = cuisineType, mealType = typeOfMeal, health = health ).hits

    }

    override suspend fun searchRecipesByCuisineTypeAndDiet(
        query: String,
        diet: String,
        cuisineType: String
    ): List<Hit> {
        return api.searchRecipesByCuisineTypeAndDiet(query = query, diet = diet, cuisineType = cuisineType).hits
    }

    override suspend fun searchRecipesByMealTypeAndDiet(
        query: String,
        diet: String,
        mealType: String
    ): List<Hit> {
        return api.searchRecipesByMealTypeAndDiet(query = query, diet = diet, mealType = mealType).hits
    }

    override suspend fun searchRecipesByMealTypeAndCuisineType(
        query: String,
        cuisineType: String,
        mealType: String
    ): List<Hit> {
        return api.searchRecipesByMealTypeAndCuisineType(query = query, cuisineType = cuisineType, mealType = mealType).hits
    }

    override suspend fun getRecipes(mealType: String): List<Hit> {
        return api.getRecipes( mealType = mealType ).hits
    }

    override suspend fun getRecipesByCuisineType(cuisineType: String ): List<Hit> {
        return api.getRecipesByCuisineType( cuisineType = cuisineType ).hits
    }

    override suspend fun getRecipeInfo(query: String): Hit {
        return api.getRecipeInfo( query = query )
    }
}


