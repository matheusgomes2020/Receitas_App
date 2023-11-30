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
    var dietIsActive: Boolean = false,
    var cuisineTypeIsActive: Boolean = false,
    var mealTypeIsActive: Boolean = false
)
@HiltViewModel
class SearchRecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase,
    private val searchRecipesByAll: SearchRecipesByAllUseCase,
    private val searchRecipesUseCaseByMealType: SearchRecipesUseCaseByMealType,
    private val searchRecipesUseCaseByDiet: SearchRecipesUseCaseByDiet,
    private val searchRecipesUseCaseByCuisineType: SearchRecipesUseCaseCuisineType,
    private val searchRecipesUseCaseCuisineTypeAndDiet: SearchRecipesUseCaseCuisineTypeAndDiet,
    private val searchRecipesUseCaseCuisineTypeAndTypeOfMeal: SearchRecipesUseCaseCuisineTypeAndTypeOfMeal,
    private val searchRecipesUseCaseDietAndTypeOfMeal: SearchRecipesUseCaseDietAndTypeOfMeal,
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipesByCuisineTypeUseCase: GetRecipesByCuisineTypeUseCase,
    savedStateHandle: SavedStateHandle


    ): ViewModel() {

    data class UiState2(
        var diet: String = "",
        var cuisineType: String = "",
        var mealType: String = "",
        var dietIsActive: Boolean = false,
        var cuisineTypeIsActive: Boolean = false,
        var mealTypeIsActive: Boolean = false
    )

    private val _state = mutableStateOf(HitListState())
    val state: State<HitListState> = _state

    private val _stateCuisineType = mutableStateOf(HitListState())
    val stateCuisineTyp: State<HitListState> = _stateCuisineType

    private val _uiState2 = mutableStateOf(UiState2())
    val uiState2: State<UiState2> = _uiState2

//    private val _stateCuisineTypeActive = MutableStateFlow(BooleanState())
//    val stateCuisineTypeActive = _stateCuisineTypeActive.asStateFlow()
//    private val _stateDietActive = MutableStateFlow(BooleanState())
//    val stateDietActive= _stateDietActive.asStateFlow()
//    private val _stateMealTypeActive = MutableStateFlow(BooleanState())
//    val stateMealTypeActive = _stateMealTypeActive.asStateFlow()



    init {
        savedStateHandle.get<FilterData2>( KEY_FILTER_DATA )?.let { filterData ->
            _uiState2.value.diet = filterData.diet
            _uiState2.value.cuisineType = filterData.cuisineType
            _uiState2.value.mealType = filterData.mealType
            _uiState2.value.dietIsActive = filterData.dietIsActive
            _uiState2.value.cuisineTypeIsActive = filterData.cuisineTypeIsActive
            _uiState2.value.mealTypeIsActive = filterData.mealTypeIsActive

            }

       // Log.d("VAMOSPRODUZIR?", "MODELLLL: :${stateMealTypeActive.value}|${stateDietActive.value}|${stateCuisineTypeActive.value}")
        getRecipesByTypeOfMeal("Dinner")
    }

    fun selectDiet(isActive: Boolean, label: String) {
        _uiState2.value.diet = label
        _uiState2.value.dietIsActive = isActive
    }
    fun selectCuisineType(isActive: Boolean, label: String) {
        _uiState2.value.cuisineType = label
        _uiState2.value.cuisineTypeIsActive = isActive
    }

    fun selectMealType(isActive: Boolean, label: String) {
        _uiState2.value.mealType = label
        _uiState2.value.mealTypeIsActive = isActive

    }


    fun searchRecipes( query: String, diet: String, mealType: String, cuisineType: String ) {
        Log.d("VAMOSPRODUZIR", "|||||||:  $cuisineType ||||| $diet |||$mealType")

        Log.d("RECEITA", "searchRecipes: chama view model")


         if (uiState2.value.mealTypeIsActive && _uiState2.value.dietIsActive &&  _uiState2.value.cuisineTypeIsActive ) {
            Log.d("VAMOSPRODUZIR", "all:  $cuisineType ||||| $diet$mealType")

            searchRecipesByAll( query, diet = diet, cuisineType = cuisineType, typeOfMeal = mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiState2.value.cuisineTypeIsActive && _uiState2.value.mealTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "cui = meal:  $cuisineType ||||| $diet||||$mealType")
            searchRecipesUseCaseCuisineTypeAndTypeOfMeal( query, cuisineType = cuisineType, typeOfMeal = mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiState2.value.cuisineTypeIsActive && _uiState2.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "cuis + diet:  $cuisineType ||||| $diet$mealType")
            searchRecipesUseCaseCuisineTypeAndDiet( query, cuisineType = cuisineType, diet = diet ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        }
        else if (uiState2.value.mealTypeIsActive && _uiState2.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "meal + diet:  $cuisineType ||||| $diet|||$mealType")

            searchRecipesUseCaseDietAndTypeOfMeal( query, diet = cuisineType, typeOfMeal = mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        }   else if (_uiState2.value.dietIsActive) {
            Log.d("VAMOSPRODUZIR", "diet:  $cuisineType ||||| $diet|||$mealType")
            searchRecipesUseCaseByDiet( query, diet ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiState2.value.mealTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "meal:  $cuisineType ||||| $diet|||$mealType")
            searchRecipesUseCaseByMealType( query, mealType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else if (uiState2.value.cuisineTypeIsActive) {
            Log.d("VAMOSPRODUZIR", "cuis:  $cuisineType ||||| $diet|||$mealType")

            searchRecipesUseCaseByCuisineType( query, cuisineType ).onEach {result ->
                when (result) {
                    is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                    is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                    is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                } }.launchIn(viewModelScope)
        } else {
             searchRecipesUseCase( query ).onEach {result ->
                 when (result) {
                     is Resource.Success -> { _state.value = HitListState(recipes = result.data ?: emptyList()) }
                     is Resource.Error -> { _state.value = HitListState(error = result.message ?: "An unexpected error occured") }
                     is Resource.Loading -> { _state.value = HitListState(isLoading = true) }
                 } }.launchIn(viewModelScope)
         }
    }

     fun getRecipesByTypeOfMeal(mealType: String ) {
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