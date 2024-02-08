package com.matheus.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.matheus.core.data.repository.RecipesRepository2
import com.matheus.core.domain.model.Recipe2
import com.matheus.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRecipesUseCase2{

    operator fun invoke(params: GetRecipesParams): Flow<PagingData<Recipe2>>
    data class GetRecipesParams(val query: String, val pagingConfig: PagingConfig)
}
class GetRecipesUseCase2Impl @Inject constructor(
    private val recipesRepository: RecipesRepository2
) : PagingUseCase<GetRecipesUseCase2.GetRecipesParams, Recipe2>(),
GetRecipesUseCase2{

    override fun createFlowObservable(params: GetRecipesUseCase2.GetRecipesParams): Flow<PagingData<Recipe2>> {
        val pagingSource = recipesRepository.getRecipes(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }



}