package com.matheus.receitasapp.presentation.filters.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.filters.ChipGroup
import com.matheus.receitasapp.presentation.filters.FilterData
import com.matheus.receitasapp.presentation.filters.FilterViewModel
import com.matheus.receitasapp.presentation.searchRecipes.SearchRecipesViewModel
import com.matheus.receitasapp.presentation.searchRecipes.components.cuisineTypesList
import com.matheus.receitasapp.presentation.searchRecipes.components.dietTypesList
import com.matheus.receitasapp.presentation.searchRecipes.components.healthTypesList
import com.matheus.receitasapp.presentation.searchRecipes.components.mealTypesList
import com.matheus.receitasapp.ui.theme.DarkGrey11

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSideSheet(
    query: String,
    viewModel: FilterViewModel =  hiltViewModel(),
    searchRecipesViewModel: SearchRecipesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    isSystemInDarkTheme: Boolean,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
) {

    var stateFilterCuisineType = searchRecipesViewModel.uiFiltersState.value.cuisineType
    var stateFilterTypeOfMeal = searchRecipesViewModel.uiFiltersState.value.mealType
    var stateFilterDiet = searchRecipesViewModel.uiFiltersState.value.diet
    var stateFilterHealth = searchRecipesViewModel.uiFiltersState.value.health

    data class StateSelected(
        var isSelected: Boolean = false,
        val label: String = ""
    )

    var mealTypeIsSelected by remember { mutableStateOf(StateSelected()) }
    var dietIsSelected by remember { mutableStateOf(StateSelected()) }
    var cuisineTypeIsSelected by remember { mutableStateOf(StateSelected()) }
    var healthIsSelected by remember { mutableStateOf(StateSelected()) }

    if ( dietIsSelected.isSelected )  viewModel.selectDiet( diet = dietIsSelected.label )
    if ( cuisineTypeIsSelected.isSelected ) viewModel.setCuisineType( cuisineType = cuisineTypeIsSelected.label )
    if ( mealTypeIsSelected.isSelected ) viewModel.selectMealType( mealType = mealTypeIsSelected.label )
    if ( healthIsSelected.isSelected ) viewModel.selectHealth( health = healthIsSelected.label )

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        sheetState = bottomSheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        )
        {
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
                SubtitleHeader(title = "Meal type", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroup( filter = stateFilterTypeOfMeal, list = mealTypesList, onSelected = {
                    viewModel.selectMealType( mealType = it )
                } )
                SubtitleHeader(title = "Region food", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroup( filter = stateFilterCuisineType, list = cuisineTypesList, onSelected = {
                    viewModel.setCuisineType( cuisineType = it )
                } )
                SubtitleHeader(title = "Diet", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroup( filter = stateFilterDiet, list = dietTypesList, onSelected = {
                    viewModel.selectDiet( diet = it )
                } )
                SubtitleHeader(title = "Health", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroup( filter = stateFilterHealth, list = healthTypesList, onSelected = {
                    viewModel.selectHealth( health = it )
                } )
                ButtonsFilters(onClickButtonReset = {
                    viewModel.resetFilters()
                    SaveFilters(viewModel, searchRecipesViewModel, query)
                    onDismiss()
                },
                    onClickButtonApply = {
                        SaveFilters(viewModel, searchRecipesViewModel, query)
                        onDismiss()
                    })
            }
        }
    }
}

private fun SaveFilters(
    viewModel: FilterViewModel,
    searchRecipesViewModel: SearchRecipesViewModel,
    query: String
) {
    val filterData = FilterData(
        diet = viewModel.uiState.value.diet,
        cuisineType = viewModel.uiState.value.cuisineType,
        mealType = viewModel.uiState.value.mealType,
        health = viewModel.uiState.value.health
    )
    if (!filterData.diet.isNullOrEmpty()) searchRecipesViewModel.selectDiet(true, filterData.diet
    ) else searchRecipesViewModel.selectDiet(false, "")
    if (!filterData.cuisineType.isNullOrEmpty()) searchRecipesViewModel.selectCuisineType(true, filterData.cuisineType
    ) else searchRecipesViewModel.selectCuisineType(false, "")
    if (!filterData.mealType.isNullOrEmpty()) searchRecipesViewModel.selectMealType(true, filterData.mealType)
    else searchRecipesViewModel.selectMealType(false, "")
    if (!filterData.health.isNullOrEmpty()) searchRecipesViewModel.selectHealth(true, filterData.health
    )     else searchRecipesViewModel.selectHealth(false, "")
    searchRecipesViewModel.searchRecipes(
        query = query,
        diet = filterData.diet,
        cuisineType = filterData.cuisineType,
        mealType = filterData.mealType,
        health = filterData.health
    )
}
