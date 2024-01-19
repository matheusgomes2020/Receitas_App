package com.matheus.receitasapp.framework

import androidx.paging.PagingSource
import com.matheus.core.data.repository.RecipesRemoteDataSource
import com.matheus.core.data.repository.RecipesRepository2
import com.matheus.core.domain.model.Recipe2
import com.matheus.receitasapp.framework.network.response.DataWrapperResponse
import com.matheus.receitasapp.framework.paging.RecipesPagingSource
import javax.inject.Inject

class RecipesRepositoryImpl2 @Inject constructor(
    private val remoteDataSource: RecipesRemoteDataSource<DataWrapperResponse>
) : RecipesRepository2 {
    override fun getRecipes(query: String): PagingSource<Int, Recipe2> {
        return RecipesPagingSource(remoteDataSource, query)
    }
}