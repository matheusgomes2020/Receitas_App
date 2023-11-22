package com.matheus.receitasapp.presentation.home

import com.matheus.receitasapp.presentation.searchRecipes.HitListState

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesUseCase
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesByCuisineTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipesByCuisineTypeUseCase: GetRecipesByCuisineTypeUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(HitListState())
    val state: State<HitListState> = _state

    private val _stateCuisineType = mutableStateOf(HitListState())
    val stateCuisineTyp: State<HitListState> = _stateCuisineType

    init {
            getRecipesByTypeOfMeal( "breakFast" )
            getRecipeByCuisineType( "South American" )
        }

    private fun getRecipesByTypeOfMeal(mealType: String ) {
        getRecipesUseCase( mealType ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HitListState(recipes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HitListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HitListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecipeByCuisineType(cuisineType: String ) {
        getRecipesByCuisineTypeUseCase( cuisineType ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateCuisineType.value = HitListState(recipes = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateCuisineType.value = HitListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateCuisineType.value = HitListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}




