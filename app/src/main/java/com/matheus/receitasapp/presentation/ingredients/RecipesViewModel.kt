package com.matheus.receitasapp.presentation.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.matheus.core.domain.model.Recipe2
import com.matheus.core.usecase.GetRecipesUseCase2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase2
) : ViewModel() {

    private val _recipesState: MutableStateFlow<PagingData<Recipe2>> = MutableStateFlow(value = PagingData.empty())
    private val _recipesState2: MutableStateFlow<PagingData<Recipe2>> = MutableStateFlow(value = PagingData.empty())
    val recipesState: MutableStateFlow<PagingData<Recipe2>> get() = _recipesState
    val recipesState2: MutableStateFlow<PagingData<Recipe2>> get() = _recipesState2

    init {
        onEvent(HomeEvent.GetHome)
        onEvent2(HomeEvent2.GetHome2)
    }

    private fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetHome -> {
                    recipesPagingData("tomato")
                }
            }
        }
    }

    private fun onEvent2(event: HomeEvent2) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent2.GetHome2 -> {
                    recipesPagingData2("milk")
                }
            }
        }
    }

     suspend fun recipesPagingData(query: String) {
         getRecipesUseCase(
            GetRecipesUseCase2.GetRecipesParams(query, getPageConfig())
        ).cachedIn(viewModelScope).collect{
            _recipesState.value = it
        }
    }

    private suspend fun recipesPagingData2(query: String) {
        getRecipesUseCase(
            GetRecipesUseCase2.GetRecipesParams(query, getPageConfig())
        ).cachedIn(viewModelScope).collect{
            _recipesState2.value = it
        }
    }

    fun recipesPagingDataMaster(query: String): Flow<PagingData<Recipe2>> {
        return getRecipesUseCase(
            GetRecipesUseCase2.GetRecipesParams(query, getPageConfig())
        ).cachedIn(viewModelScope)
    }


    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )

}

sealed class HomeEvent {
    object GetHome : HomeEvent()
}

sealed class HomeEvent2 {
    object GetHome2 : HomeEvent2()
}