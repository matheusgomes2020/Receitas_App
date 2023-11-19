package com.matheus.receitasapp.data.repository

import android.util.Log
import com.matheus.receitasapp.data.remote.RecipeApi
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.data.remote.dto.Recipe
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
        Log.d("VENTO", "getRecipeInfo:$mealType | ${api.getRecipes(mealType = mealType).hits}")
    }

    override suspend fun getRecipes2( cuisineType: String ): List<Hit> {
        return api.getRecipes2( cuisineType = cuisineType ).hits
        Log.d("VENTO", "getRecipeInfo:$cuisineType | ${api.getRecipes(mealType = cuisineType).hits}")
    }

    override suspend fun getRecipeInfo(query: String): Hit {
        return api.getRecipeInfo( query = query )
        Log.d("VAILOGO", "getRecipeInfo:$query ")
    }
}


