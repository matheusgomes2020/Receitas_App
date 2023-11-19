package com.matheus.receitasapp.data.remote

import com.matheus.receitasapp.common.Constants.API_KEY
import com.matheus.receitasapp.common.Constants.APP_ID
import com.matheus.receitasapp.common.Constants.TYPE
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.data.remote.dto.WHAT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {


    //https://api.edamam.com/api/recipes/v2?type=any&q=chicken&app_id=4b5c7b4a&app_key=15a90ed1dd0459114f6e5aab67781066
    @GET("/api/recipes/v2/")
    suspend fun searchRecipes(
        @Query("type") type: String = TYPE,
        @Query("q") query: String,
        @Query("app_id") apId: String = APP_ID,
        @Query("app_key") appKey: String = API_KEY,

        ): WHAT

    //https://api.edamam.com/api/recipes/v2/a7c379c59775dd0c7c88710f7fecff81?type=public&app_id=4b5c7b4a&app_key=15a90ed1dd0459114f6e5aab67781066
    //@GET("/api/recipes/v2/{recipeId}")
    @GET("https://api.edamam.com/api/recipes/v2/{recipeId}?type=public&app_id=4b5c7b4a&app_key=15a90ed1dd0459114f6e5aab67781066")
    suspend fun getRecipeInfo(
        @Path("recipeId") query: String,
        //@Query("type") type: String = TYPE,
       // @Query("app_id") apId: String = APP_ID,
       // @Query("app_key") appKey: String = API_KEY
    ): Hit

    //https://api.edamam.com/api/recipes/v2?type=any&app_id=4b5c7b4a&app_key=15a90ed1dd0459114f6e5aab67781066&mealType=Breakfast
    @GET("/api/recipes/v2/")
    suspend fun getRecipes(
        @Query("type") type: String = TYPE,
        @Query("q") query: String = " ",
        @Query("app_id") apId: String = APP_ID,
        @Query("app_key") appKey: String = API_KEY,
        @Query("mealType") mealType: String,
        @Query("random") random: String = "true"
        ): WHAT

    @GET("/api/recipes/v2/")
    suspend fun getRecipes2(
        @Query("type") type: String = TYPE,
        @Query("q") query: String = " ",
        @Query("app_id") apId: String = APP_ID,
        @Query("app_key") appKey: String = API_KEY,
        @Query("cuisineType") cuisineType: String,
        @Query("random") random: String = "true"
    ): WHAT

}