package com.matheus.receitasapp.presentation.searchRecipes

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.AppBarWithBack
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.common.RecipeSearchItemShimmer
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme
import com.matheus.receitasapp.ui.theme.fontFamily2
import com.matheus.receitasapp.ui.theme.fontFamily3
import kotlin.math.roundToInt

@Composable
fun SC2(){

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

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
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
    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Receitas",
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
                Spacer(modifier = Modifier.height(DpDimensions.Normal))
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

@Composable
fun RecipeSearchItem( navController: NavController, recipe: Recipe ) {
    val totalTime = recipe.totalTime.roundToInt().toString()
    val calories = recipe.calories.roundToInt().toString()
    var s: String = recipe.uri
    var s1: String = s.substring(s.indexOf("_")+1)
    Log.d("EROS", "SearchRecipesScreen: $s1")
    Surface(
        modifier = Modifier
            .clickable {
                navController
                    .navigate(NavDestinations.RecipeDetails.RECIPE_DETAILS + "/${s1}")
            }
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(DpDimensions.Smallest),
        // shadowElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(DpDimensions.Smallest)
                .background(Color.White),
          //  contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = rememberAsyncImagePainter(
                    model = recipe.images.THUMBNAIL.url,
                ), contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
//                        .width(100.dp)
//                        .height(130.dp)
                        .clip(RoundedCornerShape(DpDimensions.Small)),
                    contentScale = ContentScale.Crop

                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                    modifier = Modifier
                        .padding(DpDimensions.Smallest) ) {
                    Text(
                        modifier = Modifier
                            .width(245.dp),
                        text = recipe.label,
                        fontFamily = fontFamily2,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        fontSize = 16.sp,)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Icon(tint = Grey46, painter = painterResource(id = R.drawable.time), contentDescription = "",
                                modifier = Modifier
                                    // .background(Color.White)
                                    //.padding(DpDimensions.Small)
                                    .size(13.dp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding( top = 3.dp ),
                                text = "$totalTime min",
                                fontSize = 13.sp,
                                fontFamily = fontFamily3,
                                color = Grey46)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Icon(tint = Grey46, painter = painterResource(id = R.drawable.calories), contentDescription = "",
                                modifier = Modifier
                                    // .background(Color.White)
                                    //.padding(DpDimensions.Small)
                                    .size(13.dp)
                            )
                            Text(modifier = Modifier
                                .padding( top = 3.dp ),
                                text = "$calories cals",
                                fontSize = 13.sp,
                                fontFamily = fontFamily3,
                                color = Grey46)
                        }
                    }
                }
                Icon(tint = Grey46, painter = painterResource(id = R.drawable.chevron_right), contentDescription = "",
                    modifier = Modifier
                        // .background(Color.White)
                        .padding(start = 10.dp, end = DpDimensions.Smallest)
                        .size(13.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun E() {
    Surface(
        shape = RoundedCornerShape(DpDimensions.Smallest),
       // shadowElevation = 3.dp,
    ) {
//        Box(
//            modifier = Modifier
//            .padding( DpDimensions.Smallest),
//            contentAlignment = Alignment.Center
//        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.food), contentDescription = "",
                    modifier = Modifier
                        .size(70.dp)
//                        .width(100.dp)
//                        .height(130.dp)
                        .clip(RoundedCornerShape(DpDimensions.Dp40)),
                    contentScale = ContentScale.Crop

                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                    modifier = Modifier
                        .padding(DpDimensions.Smallest) ) {
                    Text(
                        modifier = Modifier
                            .width(270.dp),
                        text = "Potato sssssssssssssssssssssssssssChips",
                        fontFamily = fontFamily2,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        fontSize = 13.sp,)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Icon(tint = Grey46, painter = painterResource(id = R.drawable.time), contentDescription = "",
                                modifier = Modifier
                                    // .background(Color.White)
                                    //.padding(DpDimensions.Small)
                                    .size(11.dp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding( top = 3.dp ),
                                text = "30 min",
                                fontSize = 10.sp,
                                fontFamily = fontFamily3,
                                color = Grey46)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Icon(tint = Grey46, painter = painterResource(id = R.drawable.calories), contentDescription = "",
                                modifier = Modifier
                                    // .background(Color.White)
                                    //.padding(DpDimensions.Small)
                                    .size(11.dp)
                            )
                            Text(modifier = Modifier
                                .padding( top = 3.dp ),
                                text = "230 cals",
                                fontSize = 10.sp,
                                fontFamily = fontFamily3,
                                color = Grey46)
                        }
                    }
                }
                Icon(tint = Grey46, painter = painterResource(id = R.drawable.chevron_right), contentDescription = "",
                    modifier = Modifier
                        // .background(Color.White)
                        .padding(start = 10.dp, end = DpDimensions.Smallest)
                        .size(11.dp)
                )
         //   }

        }
    }
}


@Preview
@Composable
fun RecipeListItem(
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(DpDimensions.Smallest)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = R.drawable.food
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "recipe search image",
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = DpDimensions.Smallest)
        ) {
            Text(text = "Chicken Vesuvio",
                fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painterResource(id =   R.drawable.baseline_access_time_24), contentDescription = "clock icon",
                    modifier = Modifier
                        .size(15.dp)
                        .padding(end = DpDimensions.Smallest))
                Text(text = "60min",
                    fontSize = 10.sp)
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

            }
        }
    }

}


@Composable
fun SearchBar(
    placeholder: String,
    modifier: Modifier = Modifier,
    searchState: SearchState,
    onSearch: (String) -> Unit = {}
) {

   Surface(
        shape = RoundedCornerShape(DpDimensions.Small),
        //color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier,
       shadowElevation = 3.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DpDimensions.Normal)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search icon",
               // tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.size(DpDimensions.Dp20)
            )


            TextField(
                value = searchState.query, onValueChange = { value ->
                    onSearch(value)
                },
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(DpDimensions.Dp50),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Icon(
                painter = painterResource(id = R.drawable.adjustments),
                contentDescription = "search icon",
                // tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.size(DpDimensions.Dp20)
            )
        }
    }
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

