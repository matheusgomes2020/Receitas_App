package com.matheus.receitasapp.presentation.home

import com.matheus.receitasapp.presentation.searchRecipes.HitListState

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.common.Constants.MEAL_TYPE
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesUseCase
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesUseCase2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipesUseCase2: GetRecipesUseCase2,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(HitListState())
    val state: State<HitListState> = _state

    private val _state2 = mutableStateOf(HitListState())
    val state2: State<HitListState> = _state2

    init {
//        savedStateHandle.get<String>( MEAL_TYPE )?.let { mealType ->
            getRecipes( "breakFast" )
            getRecipes2( "South American" )
        }
//    }

    fun getRecipes(mealType: String ) {
        Log.d("VENTO", "searchRecipes: chama view model")
        getRecipesUseCase( mealType ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state.value = HitListState(recipes = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state.value = HitListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                   // Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state.value = HitListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRecipes2(cuisineType: String ) {
        Log.d("VENTO", "searchRecipes: chama view model")
        getRecipesUseCase2( cuisineType ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state2.value = HitListState(recipes = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state2.value = HitListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    // Log.d("VENTO", "searchRecipes: resultado${result}")

                    _state2.value = HitListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}




