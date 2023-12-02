package com.matheus.receitasapp.presentation.searchRecipes

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.common.AppBarWithBack
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.common.RecipeSearchItemShimmer
import com.matheus.receitasapp.presentation.filters.components.FilterSideSheet
import com.matheus.receitasapp.presentation.searchRecipes.components.RecipeSearchItem
import com.matheus.receitasapp.presentation.searchRecipes.components.RowSearchType
import com.matheus.receitasapp.presentation.searchRecipes.components.SearchBar
import com.matheus.receitasapp.presentation.searchRecipes.components.mealTypes
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme


@Composable
fun RowType(
    type: RowSearchType,
    selectedd: Boolean,
    //onType: (String) -> Unit,
    onSelected: (Boolean) -> Unit
){
    var selected = remember {
        mutableStateOf(selectedd)
    }
    val color = remember {
    mutableStateOf(
            Color.White
    )
}
    Surface(
        onClick = {
            if (selected.value) {
                selected.value = false
                color.value = Color.White
            } else {
                selected.value = true
                color.value = GreenApp
            }
            //onType(type.label)
            onSelected(selected.value)
            Log.d("VAMOSPRODUZIR", "getRecipeByCuisineType: ${type.label} ! $selected")

        },
        color = color.value,
        modifier = Modifier
            .padding(end = DpDimensions.Small),
        shape = RoundedCornerShape(DpDimensions.Smallest),
        shadowElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier
                .padding( horizontal = DpDimensions.Small, vertical = DpDimensions.Smallest )
        ) {
            Text(text = type.label)
        }
    }
}

@Composable
fun RowTypeT(
    title: String,
    selectedd: Boolean,
    //onType: (String) -> Unit,
    onSelected: (Boolean) -> Unit
){
    var selected = remember {
        mutableStateOf(selectedd)
    }
    val color = remember {
        mutableStateOf(
            Color.White
        )
    }
    Surface(
        onClick = {
            if (selected.value) {
                selected.value = false
                color.value = Color.White
            } else {
                selected.value = true
                color.value = GreenApp
            }
            //onType(type.label)
            onSelected(selected.value)
            Log.d("VAMOSPRODUZIR", "getRecipeByCuisineType: ${title} ! $selected")

        },
        color = color.value,
        modifier = Modifier
            .padding(end = DpDimensions.Small),
        shape = RoundedCornerShape(DpDimensions.Smallest),
        shadowElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier
                .padding( horizontal = DpDimensions.Small, vertical = DpDimensions.Smallest )
        ) {
            Text(text = title)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipesScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    searchRecipesViewModel: SearchRecipesViewModel =  hiltViewModel(),
) {

    val mealTypeIsActive = searchRecipesViewModel.uiFiltersState.value.mealType
    val cuisineTypeIsActive = searchRecipesViewModel.uiFiltersState.value.cuisineType
    val dietIsActive = searchRecipesViewModel.uiFiltersState.value.diet
    val healthIsActive = searchRecipesViewModel.uiFiltersState.value.health

    val mealTypesSearch = listOf(RowSearchType(mealTypeIsActive), RowSearchType(cuisineTypeIsActive), RowSearchType(dietIsActive), RowSearchType(healthIsActive))

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    var isFilterSheetOpen by rememberSaveable { mutableStateOf(false) }
    var queryRemember by rememberSaveable { mutableStateOf("") }
    val bottomSheetState = rememberModalBottomSheetState()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }
    var stateSearch by remember { mutableStateOf(SearchState()) }
    val state = searchRecipesViewModel.state.value
    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Recipes",
                backIcon = Icons.Default.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        )
        {
            CustomPadding(
                verticalPadding = DpDimensions.Normal,
                horizontalPadding = DpDimensions.Normal
            ) {
                SearchBar(placeholder = "search", searchState = stateSearch, onSearch =  {
                    queryRemember = it
                    stateSearch = stateSearch.copy(query = it)
                    searchRecipesViewModel.searchRecipes(it, cuisineType = cuisineTypeIsActive, diet = dietIsActive, mealType = mealTypeIsActive, health = healthIsActive  ) },
                    onSettingsClick = {
                        isFilterSheetOpen = true
                    })
                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
                ){
                    items( mealTypesSearch ) { type ->
                        if (type.label != "") {
                            RowType( type, false,
                                onSelected = {})
                        }
                    }
                }
                state.recipes?.let {
                    LazyColumn(modifier = Modifier
                        .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                            items(state.recipes) { hit ->
                                RecipeSearchItem( navController = navController, recipe = hit.recipe )
                            }
                    }
                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                if (state.isLoading) {

                    LazyColumn(modifier = Modifier
                        .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(10) {
                            RecipeSearchItemShimmer()
                        }
                    }
                }
            }
        }
    }
    if (isFilterSheetOpen) {
        FilterSideSheet(
            query = queryRemember,
            isSystemInDarkTheme = isSystemInDarkTheme,
            bottomSheetState = bottomSheetState,
            onDismiss = { isFilterSheetOpen = false }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
fun SC(){
    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Receitas",
                backIcon = Icons.Default.ArrowBack,
                onBackClick = {
                   // navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    //color = if (useDarkIcons)
                    color = if (isSystemInDarkTheme())
                        Color.White else DarkGrey11
                )
        )
        {

            CustomPadding(
                verticalPadding = DpDimensions.Normal,
                horizontalPadding = DpDimensions.Normal
            ) {
                SearchBar(placeholder = "search", searchState = SearchState() ) {
                }
                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
                    ){
                    items( mealTypes ) { type ->
                        RowType(  type, false, {},
                          //  {}
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun SuggestionChipExample() {
    SuggestionChip(
        onClick = { Log.d("Suggestion chip", "hello world") },
        label = { Text("Suggestion chip") }
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    ReceitasAppTheme {
        SearchBar(
            placeholder = "search",
            searchState = SearchState(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

