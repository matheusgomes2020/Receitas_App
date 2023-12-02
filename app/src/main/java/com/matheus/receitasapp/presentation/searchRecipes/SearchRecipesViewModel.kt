package com.matheus.receitasapp.presentation.searchRecipes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesByCuisineTypeUseCase
import com.matheus.receitasapp.domain.use_case.get_recipes.GetRecipesUseCase
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesByAllUseCase
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCase
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseByDiet
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseByHealth
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseByMealType
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseCuisineType
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseCuisineTypeAndDiet
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseCuisineTypeAndTypeOfMeal
import com.matheus.receitasapp.domain.use_case.search_recipes.SearchRecipesUseCaseDietAndTypeOfMeal
import com.matheus.receitasapp.presentation.filters.KEY_FILTER_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class FilterData2(
    var diet: String = "",
    var cuisineType: String = "",
    var mealType: String = "",
    var health: String = "",
    var dietIsActive: Boolean = false,
    var cuisineTypeIsActive: Boolean = false,
    var mealTypeIsActive: Boolean = false,
    var healthIsActive: Boolean = false
)
@HiltViewModel
class SearchRecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val searchRecipesByAll: SearchRecipesByAllUseCase,
    private val searchRecipesUseCaseByMealType: SearchRecipesUseCaseByMealType,
    private val searchRecipesUseCaseByDiet: SearchRecipesUseCaseByDiet,
    private val searchRecipesUseCaseByHealth: SearchRecipesUseCaseByHealth,
    private val searchRecipesUseCaseByCuisineType: SearchRecipesUseCaseCuisineType,
    private val searchRecipesUseCaseCuisineTypeAndDiet: SearchRecipesUseCaseCuisineTypeAndDiet,
    private val searchRecipesUseCaseCuisineTypeAndTypeOfMeal: SearchRecipesUseCaseCuisineTypeAndTypeOfMeal,
    private val searchRecipesUseCaseDietAndTypeOfMeal: SearchRecipesUseCaseDietAndTypeOfMeal,
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipesByCuisineTypeUseCase: GetRecipesByCuisineTypeUseCase,
    savedStateHandle: SavedStateHandle


    ): ViewModel() {

    data class UiFiltersState(
        var diet: String = "",
        var cuisineType: String = "",
        var mealType: String = "",
        var health: String = "",
        var dietIsActive: Boolean = false,
        var cuisineTypeIsActive: Boolean = false,
        var mealTypeIsActive: Boolean = false,
        var healthIsActive: Boolean = false
    )

    private val _state = mutableStateOf(HitListState())
    val state: State<HitListState> = _state

    private val _stateCuisineType = mutableStateOf(HitListState())
    val stateCuisineTyp: State<HitListState> = _stateCuisineType

    private val _uiFiltersState = mutableStateOf(UiFiltersState())
    val uiFiltersState: State<UiFiltersState> = _uiFiltersState

    init {
        savedStateHandle.get<FilterData2>( KEY_FILTER_DATA )?.let { filterData ->
            _uiFiltersState.value.diet = filterData.diet
            _uiFiltersState.value.cuisineType = filterData.cuisineType
            _uiFiltersState.value.mealType = filterData.mealType
            _uiFiltersState.value.health = filterData.health
            _uiFiltersState.value.dietIsActive = filterData.dietIsActive
            _uiFiltersState.value.cuisineTypeIsActive = filterData.cuisineTypeIsActive
            _uiFiltersState.value.mealTypeIsActive = filterData.mealTypeIsActive
            _uiFiltersState.value.healthIsActive = filterData.healthIsActive
            }

        getRecipesByTypeOfMeal("Dinner")
    }

    fun selectDiet(isActive: Boolean, label: String) {
        _uiFiltersState.value.diet = label
        _uiFiltersState.value.dietIsActive = isActive
    }
    fun selectCuisineType(isActive: Boolean, label: String) {
        _uiFiltersState.value.cuisineType = label
        _uiFiltersState.value.cuisineTypeIsActive = isActive
    }

    fun selectMealType(isActive: Boolean, label: String) {
        _uiFiltersState.value.mealType = label
        _uiFiltersState.value.mealTypeIsActive = isActive
    }

    fun selectHealth(isActive: Boolean, label: String) {
        _uiFiltersState.value.health = label
        _uiFiltersState.value.healthIsActive = isActive
    }

    fun searchRecipes( query: String, diet: String, mealType: String, cuisineType: String, health: String ) {
         if (uiFiltersState.value.mealTypeIsActive && _uiFiltersState.value.dietIsActive &&  _uiFiltersState.value.cuisineTypeIsActive ) {
            Log.d("VAMOSPRODUZIR", "all:  $cuisineType ||||| $diet$mealType")

            searchRecipesByAll( query, diet = diet, cuisineType = cuisineType, typeOfMeal = mealType, health = health ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiFiltersState.value.cuisineTypeIsActive && _uiFiltersState.value.mealTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "cui = meal:  $cuisineType ||||| $diet||||$mealType")
            searchRecipesUseCaseCuisineTypeAndTypeOfMeal( query, cuisineType = cuisineType, typeOfMeal = mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiFiltersState.value.cuisineTypeIsActive && _uiFiltersState.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "cuis + diet:  $cuisineType ||||| $diet$mealType")
            searchRecipesUseCaseCuisineTypeAndDiet( query, cuisineType = cuisineType, diet = diet ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        }
        else if (uiFiltersState.value.mealTypeIsActive && _uiFiltersState.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "meal + diet:  $cuisineType ||||| $diet|||$mealType")

            searchRecipesUseCaseDietAndTypeOfMeal( query, diet = cuisineType, typeOfMeal = mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        }   else if (_uiFiltersState.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "diet:  $cuisineType ||||| $diet|||$mealType")
            searchRecipesUseCaseByDiet( query, diet ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiFiltersState.value.mealTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "meal:  $cuisineType ||||| $diet|||$mealType")
            searchRecipesUseCaseByMealType( query, mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiFiltersState.value.cuisineTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "cuis:  $cuisineType ||||| $diet|||$mealType")

            searchRecipesUseCaseByCuisineType( query, cuisineType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiFiltersState.value.healthIsActive) {
             Log.d("VAMOSPRODUZIR", "cuis:  $cuisineType ||||| $diet|||$mealType")

             searchRecipesUseCaseByHealth( query, health ).onEach {result ->
                 when (result) {
                     is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                     is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                     is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                 } }.launchIn(viewModelScope)
         }else {
             searchRecipesUseCase( query ).onEach {result ->
                 when (result) {
                     is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                     is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                     is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                 } }.launchIn(viewModelScope)
         }
    }

     private fun getRecipesByTypeOfMeal(mealType: String ) {
        getRecipesUseCase(mealType).onEach { result ->
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
}