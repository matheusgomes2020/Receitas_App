package com.matheus.receitasapp.presentation.recipe_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.common.Constants.RECIPE_ID
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.domain.use_case.get_recipe_Info.GetRecipeInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeInfoUseCase: GetRecipeInfoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

//    private val _state = mutableStateOf(RecipeDetailState())
//    val state: State<RecipeDetailState> = _state

    private val _uiState = MutableStateFlow(UiStateRecipeDetail())
    val uiState: StateFlow<UiStateRecipeDetail> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>(RECIPE_ID)?.let { recipeId ->
            Log.d("VAILOGO", ":HANDLE $recipeId")
            getRecipe( recipeId )
        }
    }

    private fun getRecipe(recipeId: String ) {
        Log.d("VAILOGO", ":chama view Model Metodo $recipeId")

        getRecipeInfoUseCase( recipeId ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            id = result.data?.recipe?.uri,
                            image = result.data?.recipe?.image,
                            totalTime = result.data?.recipe?.totalTime?.roundToInt().toString(),
                            calories = result.data?.recipe?.calories?.roundToInt().toString(),
                            label = result.data?.recipe?.label,
                            ingredients = result.data?.recipe!!.ingredients,
                            isLoading = false,
                            isError = false
                        )
                    }
//                    _state.value = RecipeDetailState(recipe = result.data)
//                    Log.d("VAILOGO", ":SUCESSO VIEW MODEL $recipeId | ${_state.value}")

                }
                is Resource.Error -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isError = true,
                            isLoading = false
                        )}
//                    _state.value = RecipeDetailState(
//                        error = result.message ?: "An unexpected error occured"
//
//                    )
//                    Log.d("VAILOGO", ":ERRO VIEW MODEL $recipeId | ${_state.value}")

                }
                is Resource.Loading -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            isError = false
                        )}
//                    _state.value = RecipeDetailState(isLoading = true)
//                    Log.d("VAILOGO", ":CARREGANDO VIEW MODEL $recipeId | ${_state.value}")

                }
            }
        }.launchIn(viewModelScope)
    }
}
