package com.matheus.receitasapp.presentation.recipes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.domain.model.InvalidRecipeException
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.domain.use_case.RecipeUseCases
import com.matheus.receitasapp.domain.use_case.room.AddRecipe
import com.matheus.receitasapp.domain.util.OrderType
import com.matheus.receitasapp.domain.util.RecipeOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor( private val recipeUseCases: RecipeUseCases)
    : ViewModel() {

    private val _state = mutableStateOf(RecipesRoomState())
    val state: State<RecipesRoomState> = _state

    private var getRecipesJob: Job? = null

    init {
        getRecipes(RecipeOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: RecipesEvent) {

        when (event) {
            is RecipesEvent.Order -> {
                if (state.value.recipeOrder::class == event.recipeOrder::class &&
                    state.value.recipeOrder.orderType == event.recipeOrder.orderType
                ) {
                    return
                }
                getRecipes(event.recipeOrder)
            }

            is RecipesEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    recipeUseCases.deleteRecipe(event.recipeRoom)
                }
            }

            else -> {

            }
        }

    }

    private fun getRecipes(recipeOrder: RecipeOrder) {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes(recipeOrder)
            .onEach { recipes ->
                Log.d("RTRTRTR", "getRecipes: $recipes")
                _state.value = state.value.copy(
                    recipes = recipes,
                    recipeOrder = recipeOrder
                )
            }
            .launchIn(viewModelScope)
    }
}


