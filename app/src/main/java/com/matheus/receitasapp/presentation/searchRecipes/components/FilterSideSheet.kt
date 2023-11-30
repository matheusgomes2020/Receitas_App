package com.matheus.receitasapp.presentation.searchRecipes.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.filters.ChipGroupTypeOfMeal
import com.matheus.receitasapp.presentation.filters.ChipGroupCompose2
import com.matheus.receitasapp.presentation.filters.ChipGroupComposeT
import com.matheus.receitasapp.presentation.filters.ChipGroupCuisineType
import com.matheus.receitasapp.presentation.filters.ChipGroupDiet
import com.matheus.receitasapp.presentation.filters.FilterData
import com.matheus.receitasapp.presentation.filters.FilterViewModel
import com.matheus.receitasapp.presentation.searchRecipes.RowType
import com.matheus.receitasapp.presentation.searchRecipes.SearchRecipesViewModel
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme

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

    var stateCuisineType = searchRecipesViewModel.uiState2.value.cuisineType
    var stateTypeOfMeal = searchRecipesViewModel.uiState2.value.mealType
    var stateDiet = searchRecipesViewModel.uiState2.value.diet

    data class StateSelected(
        var isSelected: Boolean = false,
        val label: String = ""
    )

    var mealTypeIsSelected by remember { mutableStateOf(StateSelected()) }
    var dietIsSelected by remember { mutableStateOf(StateSelected()) }
    var cuisineTypeIsSelected by remember { mutableStateOf(StateSelected()) }

    if ( dietIsSelected.isSelected )  viewModel.selectDiet( diet = dietIsSelected.label )
    if ( cuisineTypeIsSelected.isSelected ) viewModel.setCuisineType( cuisineType = cuisineTypeIsSelected.label )
    if ( mealTypeIsSelected.isSelected ) viewModel.selectMealType( mealType = mealTypeIsSelected.label )


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
                ChipGroupTypeOfMeal( ii = stateTypeOfMeal, list = mealTypesList, onSelected = {
                    viewModel.selectMealType( mealType = it )
                } )
                SubtitleHeader(title = "Region food", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroupCuisineType( ii = stateCuisineType, list = cuisineTypesList, onSelected = {
                    viewModel.setCuisineType( cuisineType = it )
                } )
                SubtitleHeader(title = "Diet", isIconVisible = false, isSystemInDarkTheme = false)
                ChipGroupDiet( ii = stateDiet, list = dietTypesList, onSelected = {
                    viewModel.selectDiet( diet = it )
                } )
                ButtonsFilters(onClickButtonReset = {
                    viewModel.resetFilters()
                    onDismiss()
                },
                    onClickButtonApply = {
                        val filterData = FilterData(
                            diet = viewModel.uiState.value.diet,
                            cuisineType = viewModel.uiState.value.cuisineType,
                            mealType = viewModel.uiState.value.mealType,
                        )
                        if ( !filterData.diet.isNullOrEmpty() ) searchRecipesViewModel.selectDiet( true, filterData.diet ) else searchRecipesViewModel.selectDiet(false, "")
                        if ( !filterData.cuisineType.isNullOrEmpty() ) searchRecipesViewModel.selectCuisineType( true, filterData.cuisineType ) else searchRecipesViewModel.selectCuisineType(false, "")
                        if ( !filterData.mealType.isNullOrEmpty() ) searchRecipesViewModel.selectMealType( true, filterData.mealType ) else searchRecipesViewModel.selectMealType(false, "")
                        searchRecipesViewModel.searchRecipes(query = query, diet = filterData.diet, cuisineType = filterData.cuisineType, mealType = filterData.mealType)
                        onDismiss()
                    })
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ReceitasAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        Color.White else DarkGrey11
                )
                .padding(DpDimensions.Normal)
        )
        {
            ButtonsFilters({}, {})
            }
    }

}

@Composable
private fun ButtonsFilters(
    onClickButtonReset: () -> Unit,
    onClickButtonApply: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DpDimensions.Normal)
    ) {
        OutlinedButton(
            onClick = {
                      onClickButtonReset()
            },
            modifier = Modifier
                .weight(1f)
                .height(41.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(DpDimensions.Small),
            border = BorderStroke(1.dp, GreenApp)
        )
        {
            Text(
                text = "Reset",
                color = GreenApp,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Button(
            onClick = {
                      onClickButtonApply()
            },
            modifier = Modifier
                .weight(1f)
                .height(41.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenApp
            ),
            shape = RoundedCornerShape(DpDimensions.Small),
        ) {
            Text(
                text = "Apply",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}