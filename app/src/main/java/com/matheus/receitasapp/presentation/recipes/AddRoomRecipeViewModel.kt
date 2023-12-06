package com.matheus.receitasapp.presentation.recipes

import com.matheus.receitasapp.domain.model.InvalidRecipeException
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.use_case.RecipeUseCases

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoomRecipeViewModel @Inject constructor(
    private val useCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipeId = mutableStateOf(RecipeInfosState())
    val recipeId: State<RecipeInfosState> = _recipeId

    private val _recipeTitle = mutableStateOf(RecipeInfosState())
    val recipeTitle: State<RecipeInfosState> = _recipeTitle

    private val _recipeImage = mutableStateOf(RecipeInfosState())
    val recipeImage: State<RecipeInfosState> = _recipeImage

    private val _recipeTime = mutableStateOf(RecipeInfosState())
    val recipeTime: State<RecipeInfosState> = _recipeTime

    private val _recipeIngredients = mutableStateOf(RecipeInfosState())
    val recipeIngredients: State<RecipeInfosState> = _recipeIngredients



    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null


    fun onEvent(event: AddRecipeEvent) {
        when(event) {
            is AddRecipeEvent.EnteredTitle -> {
                _recipeTitle.value = recipeTitle.value.copy(
                    data = event.value
                )
            }
            is AddRecipeEvent.EnteredId -> {
                _recipeId.value = recipeId.value.copy(
                    data = event.value
                )
            }
            is AddRecipeEvent.EnteredImage -> {
                _recipeImage.value = recipeImage.value.copy(
                    data = event.value
                )
            }
            is AddRecipeEvent.EnteredTime -> {
                _recipeTime.value = recipeTime.value.copy(
                    data = event.value
                )
            }
            is AddRecipeEvent.EnteredIngredients -> {
                _recipeIngredients.value = recipeIngredients.value.copy(
                    data = event.value
                )
            }

            is AddRecipeEvent.SaveRecipe -> {
                viewModelScope.launch {
                    try {
                        useCases.addRecipe(
                            RecipeRoom(
                                title = recipeTitle.value.data,
                                id = recipeId.value.data,
                                time = recipeTime.value.data,
                                image = recipeImage.value.data,
                                ingredients = recipeIngredients.value.data,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveRecipe)
                    } catch(e: InvalidRecipeException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save recipe"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveRecipe: UiEvent()
    }
}