package com.matheus.receitasapp.presentation.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.AppBarWithBackAndIcon
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.common.SubtitleHeader
import com.matheus.receitasapp.presentation.searchRecipes.RowType
import com.matheus.receitasapp.presentation.searchRecipes.components.mealTypes
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme


@Composable
fun FiltersScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
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

    Scaffold(
        topBar = {
            AppBarWithBackAndIcon(
                title = "Filters",
                icon = R.drawable.adjustments,
                icon2 = R.drawable.left_chevron_t,
                onIcon2Click = {
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
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
//                SubtitleHeader(title = "Meal type", isIconVisible = false, isSystemInDarkTheme = false )
//                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
//                ){
//                    items( mealTypes ) { type ->
//                        RowType( type, onType = {
//                        })
//                    }
//                }
//                SubtitleHeader(title = "Region food", isIconVisible = false, isSystemInDarkTheme = false )
//                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
//                ){
//                    items( mealTypes ) { type ->
//                        RowType( type, onType = {
//                        })
//                    }
//                }
//                SubtitleHeader(title = "Type of dishes", isIconVisible = false, isSystemInDarkTheme = false )
//                LazyRow(contentPadding = PaddingValues(vertical = DpDimensions.Normal),
//                ){
//                    items( mealTypes ) { type ->
//                        RowType( type, onType = {
//                        })
//                    }
                }
                var a  = listOf(
                    "dish: WWWW",
                    "cuisine: WWWW",
                    "type: WWWW"
                )

                Button(onClick = { }) {
                    navController.navigate( "${NavDestinations.SearchRecipes.SEARCH_RECIPES}/$a" )
                }
            }
        }
    }

//}

@Preview
@Composable
fun AA() {
    ReceitasAppTheme {
        FiltersScreen(navController = rememberNavController(), isSystemInDarkTheme = false )
    }
}