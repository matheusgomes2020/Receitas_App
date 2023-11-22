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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
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
import com.matheus.receitasapp.presentation.searchRecipes.components.RecipeSearchItem
import com.matheus.receitasapp.presentation.searchRecipes.components.RowSearchType
import com.matheus.receitasapp.presentation.searchRecipes.components.SearchBar
import com.matheus.receitasapp.presentation.searchRecipes.components.types
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme


@Composable
fun RowType(
    type: RowSearchType,
    onSearch: (String) -> Unit
){
    var selected = remember {
        mutableStateOf(false)
    }
    val color = remember {
    mutableStateOf(
            Color.White
    )
}
    Surface(
        onClick = {
//            if (selected.value) {
//                selected.value = false
//                color.value = Color.White
//            } else {
//                selected.value = true
//                color.value = GreenApp
//            }
            onSearch(type.label)
            Log.d("ISSO", "getRecipeByCuisineType: ${type.label}")

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
fun SearchRecipesScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    searchRecipesViewModel: SearchRecipesViewModel =  hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }
    var stateSearch by remember {
        mutableStateOf(SearchState())
    }
    val state = searchRecipesViewModel.state.value
    val state2 = searchRecipesViewModel.stateCuisineTyp.value
    Log.d("ISSO", "First State Value: ${state}")
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
                SearchBar(placeholder = "search", searchState = stateSearch ) {
                    stateSearch = stateSearch.copy(query = it)
                    searchRecipesViewModel.searchRecipes(it)
                }
                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
                ){
                    items( types ) { type ->
                        RowType( type, onSearch = {searchRecipesViewModel.getRecipesByTypeOfMeal(it)
                            Log.d("ISSO", "LazyRow: $it")
                        })
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
                    items( types ) { type ->
                        RowType(  type, {} )
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

