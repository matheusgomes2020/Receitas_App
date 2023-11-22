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
    override suspend fun getRecipes(mealType: String): List<Hit> {
        return api.getRecipes( mealType = mealType ).hits
        Log.d("VENTO", "REPOSITORY:$mealType | ${api.getRecipes(mealType = mealType).hits}")
    }

    override suspend fun getRecipesByCuisineType(cuisineType: String ): List<Hit> {
        return api.getRecipesByCuisineType( cuisineType = cuisineType ).hits
        Log.d("ISSO", "REPOSITORY:$cuisineType | ${api.getRecipesByCuisineType(cuisineType = cuisineType).hits}")
    }

    override suspend fun getRecipeInfo(query: String): Hit {
        return api.getRecipeInfo( query = query )
        Log.d("VAILOGO", "getRecipeInfo:$query ")
    }
}


