package com.matheus.receitasapp.presentation.filters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val KEY_FILTER_DATA = "filter_data"

data class FilterData(
    val diet: String,
    val health: String,
    val cuisineType: String,
    val mealType: String,
    //val dishType: String,
)

class FilterViewModel(
    savedStateHandle: SavedStateHandle
):ViewModel() {
    data class UiState(
        val diet: String = "",
        val health: String = "",
        val cuisineType: String = "",
        val mealType: String = "",
       // val dishType: String = "",
        val clearAllFilters: Boolean = false,
        val applyAllFilters: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<FilterData>( KEY_FILTER_DATA )?.let { filterData ->
            _uiState.update {
                it.copy( diet = filterData.diet, cuisineType = filterData.cuisineType,
                    health = filterData.health,
                    mealType = filterData.mealType,
                   // dishType = filterData.dishType
                )
            }
        }
    }
//    fun setDishType(dishType: String) {
//        _uiState.update {
//            it.copy(dishType = dishType)
//        }
//    }

    fun selectDiet(diet: String) {
        _uiState.update {
            it.copy(diet = diet)
        }
    }

    fun deselectDiet() {
        _uiState.update {
            it.copy(diet = "")
        }
    }

    fun setCuisineType(cuisineType: String) {
        _uiState.update {
            it.copy(cuisineType = cuisineType)
        }
    }

    fun deselectCuisineType() {
        _uiState.update {
            it.copy(cuisineType = "")
        }
    }

    fun selectHealth(health: String) {
        _uiState.update {
            it.copy(health = health)
        }
    }

    fun deselectHealth() {
        _uiState.update {
            it.copy(health = "")
        }
    }

    fun selectMealType(mealType: String) {
        _uiState.update {
            it.copy(mealType = mealType)
        }
    }

    fun deselectMealType() {
        _uiState.update {
            it.copy(mealType = "")
        }
    }
    fun resetFilters() {
        _uiState.update {
            it.copy(clearAllFilters = true,
                //dishType = "",
                mealType = "",
                health = "",
                cuisineType = "", diet = "")
        }
    }

    fun applyFilters() {
        _uiState.update {
            it.copy(applyAllFilters = true)
        }
    }

}