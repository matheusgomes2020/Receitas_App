package com.matheus.receitasapp.presentation.searchRecipes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.common.Constants
import com.matheus.receitasapp.common.Constants.QUERY
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesUseCase
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchRecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,

): ViewModel() {

    private val _state = mutableStateOf(HitListState())
    val state: State<HitListState> = _state

    fun searchRecipes( query: String ) {
        Log.d("RECEITA", "searchRecipes: chama view model")
        searchRecipesUseCase( query ).onEach {result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("RECEITA", "searchRecipes: resultado${result}")

                    _state.value = HitListState(recipes = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    Log.d("RECEITA", "searchRecipes: resultado${result}")

                    _state.value = HitListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    Log.d("RECEITA", "searchRecipes: resultado${result}")

                    _state.value = HitListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}