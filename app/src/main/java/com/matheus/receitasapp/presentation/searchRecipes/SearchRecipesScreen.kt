package com.matheus.receitasapp.presentation.searchRecipes

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.matheus.receitasapp.navigation.AppGraph
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.SearchIcon
import com.matheus.receitasapp.presentation.TextSearchBar
import com.matheus.receitasapp.ui.theme.AppColor
import com.matheus.receitasapp.ui.theme.DarkGrey11
import kotlin.math.roundToInt

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
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
                var text by rememberSaveable { mutableStateOf("") }
                var active by rememberSaveable { mutableStateOf(false) }
                

                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    // .height(60.dp)
                    //.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    query = text,
                    onQueryChange = {
                        text = it
                        //searchRecipesViewModel.searchRecipes(text)
                                    },
                    onSearch = {
                        active = false
                        searchRecipesViewModel.searchRecipes(text)},
                    active = false,
                    onActiveChange = { active = it },
                    placeholder = { TextSearchBar(title = "Pesquisar receitas...") },
                    leadingIcon = { SearchIcon() },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    if (text.isNotEmpty()) text = ""
                                    searchRecipesViewModel.searchRecipes(text)
                                }
                                .size(20.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.LightGray
                        )
                    },
                    shape = RoundedCornerShape(15.dp)
                ) {
                }
                Spacer(modifier = Modifier.height(DpDimensions.Normal))
                state.recipes?.let {
                    //Text(text = state.recipes.toString())
                    Log.d("RECEITA", "searchRecipes: resultado${state.recipes}")

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                            items(state.recipes) { hit ->
                                val totalTime = hit.recipe.totalTime.roundToInt().toString()

                                var s: String = hit.recipe.uri
                                var s1: String = s.substring(s.indexOf("_")+1)
                                Log.d("EROS", "SearchRecipesScreen: $s1")
                                Row(
                                    //horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(DpDimensions.Smallest)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(DpDimensions.Normal))
                                        .background(Color.White)
                                        .clickable {
                                           // try {
                                                navController
                                                    .navigate(NavDestinations.RecipeDetails.RECIPE_DETAILS + "/${s1}")
                                                    .let {
                                                        Log.d(
                                                            "VAILOGO",
                                                            "SearchRecipesScreen: ${it.toString()} | $s1"
                                                        )
                                                    }
//                                            } catch (ex: Exception) {
//                                                Log.d(
//                                                    "VAILOGO",
//                                                    "SearchRecipesScreen: ${it.toString()} | $s1"
//                                                )
//                                                ex.printStackTrace()
//                                            }
                                        }
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            model = hit.recipe.images.THUMBNAIL.url
                                        ),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "recipe search image",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(shape = RoundedCornerShape(15.dp))
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                    ) {
                                        Text(modifier = Modifier
                                            .padding(bottom = 3.dp),
                                            text = hit.recipe.label,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            maxLines = 2)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            //horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Icon(painterResource(id =   R.drawable.baseline_access_time_24), contentDescription = "clock icon",
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .padding(end = DpDimensions.Smallest))
                                            Text(text = "$totalTime min",
                                                fontSize = 15.sp)
                                        }

                                    }
                                }
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
                    Text(
                        text = "Carregando!!!",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun E() {
    Surface(
        shape = RoundedCornerShape(DpDimensions.Smallest),
        shadowElevation = 3.dp
    ) {
//        Box(
//            modifier = Modifier
//            .padding( DpDimensions.Smallest),
//            contentAlignment = Alignment.Center) {

            Row {
                Image(painter = painterResource(id = R.drawable.food), contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(DpDimensions.Smallest))

                )
                Column(
                    modifier = Modifier
                        .padding(DpDimensions.Smallest) ) {
                    Text(text = "Potato Chips",
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        fontSize = 15.sp,)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                    ) {
                        Icon(tint = Color.Black, painter = painterResource(id = R.drawable.time), contentDescription = "",
                            modifier = Modifier
                                // .background(Color.White)
                                //.padding(DpDimensions.Small)
                                .size(13.dp)
                        )
                        Text(text = "30 min",
                           fontSize = 13.sp )
                    }
                }
           // }

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

