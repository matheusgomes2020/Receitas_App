package com.matheus.core.data.repository

interface RecipesRemoteDataSource<T> {

    suspend fun fetchRecipes(queries: Map<String, String>) : T

}