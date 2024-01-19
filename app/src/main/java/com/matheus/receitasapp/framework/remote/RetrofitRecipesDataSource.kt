package com.matheus.receitasapp.framework.remote

import com.matheus.core.data.repository.RecipesRemoteDataSource
import com.matheus.receitasapp.framework.RecipesApi2
import com.matheus.receitasapp.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitRecipesDataSource @Inject constructor(
    private val recipesApi: RecipesApi2
) : RecipesRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchRecipes(queries: Map<String, String>): DataWrapperResponse {
        return recipesApi.getRecipes(queries)
    }
}