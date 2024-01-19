package com.matheus.receitasapp.framework

import com.matheus.receitasapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipesApi2 {

    @GET("/search")
    suspend fun getRecipes(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse

}