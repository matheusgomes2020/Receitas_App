package com.matheus.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.matheus.core.data.repository.RecipesRepository2
import com.matheus.core.domain.model.Recipe2
import com.matheus.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesUseCase2 @Inject constructor(
    private val recipesRepository: RecipesRepository2
) : PagingUseCase<GetRecipesUseCase2.GetRecipesParams, Recipe2>() {

    override fun createFlowObservable(params: GetRecipesParams): Flow<PagingData<Recipe2>> {
        return Pager(config = params.pagingConfig) {
            recipesRepository.getRecipes(params.query)
        }.flow
    }

    data class GetRecipesParams(val query: String, val pagingConfig: PagingConfig)

}