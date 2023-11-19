package com.matheus.receitasapp.presentation.recipe_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.data.remote.dto.Ingredient
import com.matheus.receitasapp.presentation.recipe_detail.components.IngredientsDetail
import com.matheus.receitasapp.presentation.recipe_detail.components.ingredients
import com.matheus.receitasapp.ui.theme.AppColor
import com.matheus.receitasapp.ui.theme.GreenParsley
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.RedTomato
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //val state = viewModel.state.value



//    state.recipe.let {
//        if (it != null) {
//            val name = it.recipe.label
//            val totalIngredients = it.recipe.ingredients.size
//            val calories = it.recipe.calories.roundToInt().toString()
//            val totalTime = it.recipe.totalTime.roundToInt().toString()

    //    }
//    }
    if ( uiState.isError ) {
        Text(
            text = "Erro",
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if(uiState.isLoading) {
        Text(
            text = "Carregando!!!",
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    else{
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            uiState.image?.let { TopContainer(image = it, navController) }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
            ) {
                uiState.label?.let { uiState.calories?.let { it1 -> uiState.totalTime?.let { it2 -> DetailsContent( name = it, ingredientsQuantity = uiState.ingredients.size, calories = it1, totalTime = it2) } } }
                Spacer(modifier = Modifier.height(DpDimensions.Small))
                IngredientsContainer( uiState.ingredients)

            }

        }
    }

}
@Composable
fun TopContainer(image: String, navController: NavController){
    Surface(
        shape = RoundedCornerShape(bottomStart = DpDimensions.Dp30, bottomEnd = DpDimensions.Dp30),
        modifier = Modifier
            //.fillMaxSize()
            //.fillMaxWidth()
            .height(450.dp)
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painter = rememberAsyncImagePainter(model = image ),
                    contentScale = ContentScale.FillBounds
                )
            // .height(500.dp)
        ) {
            // Box(contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .padding(DpDimensions.Normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50)),
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.left_chevron_t), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(18.dp)
                            .clickable {
                                navController.popBackStack()
                            })
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50))
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.bookmark), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(22.dp))
                }
            }
        }
    }

}

@Composable
fun DetailsContent( name: String, ingredientsQuantity: Int, calories: String, totalTime: String ){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier,
        //.fillMaxWidth()
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier
            //.clip(RoundedCornerShape(DpDimensions.Dp30))
            .padding(vertical = DpDimensions.Normal),
            contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding( horizontal = DpDimensions.Small )
                    , text = name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
                Text(text = "$ingredientsQuantity ingredients",
                    modifier = Modifier
                        .padding(top = DpDimensions.Smallest),
                    color = Grey46)
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DpDimensions.Normal)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.time), contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                        Text(text = "$totalTime min")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.calories), contentDescription = "",
                            modifier = Modifier
                                .size(22.dp)
                        )
                        Text(text = "$calories cals")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.spoon_and_fork), contentDescription = "",
                            modifier = Modifier
                                // .background(Color.White)
                                //.padding(DpDimensions.Small)
                                .size(22.dp)
                        )
                        Text(text = "2 serves")
                    }

                }

            }
        }
    }
}


@Composable
fun IngredientsContainer( ingredients: List<Ingredient> ) {
    Column(modifier = Modifier
        .padding(vertical = DpDimensions.Small),
        verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ingredients",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = DpDimensions.Dp20)){
            items( ingredients ) { ingredient ->
                IngredientItem(ingredient)
            }
        }
    }
}

@Preview
@Composable
fun IngredientsContainerTest2() {
    Column(modifier = Modifier
        .padding(vertical = DpDimensions.Small),
        verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ingredients",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = DpDimensions.Dp20)){
            items(ingredients) { ingredient ->
                //IngredientItem(ingredient)
            }
        }
    }
}

@Composable
fun IngredientItemTest2(ingredientsDetail: IngredientsDetail) {

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            shadowElevation = 3.dp,
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(modifier = Modifier
                .background(ingredientsDetail.backgroundColor)
                .padding(DpDimensions.Normal)
                .size(80.dp)
            ){
                Image(painter = painterResource(ingredientsDetail.image), contentDescription = "" )
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column {
            Text(text = ingredientsDetail.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp)
            Text(text = "1 item",
                color = Grey46,
                fontSize = 13.sp)
        }
    }
}


@Composable
fun IngredientItem(ingredient: Ingredient) {
    val itemQuantity = ingredient.quantity.roundToInt().toString()
    var meassure = ""
    meassure = if (ingredient.measure == "<unit>") "unit" else if (ingredient.measure == null) "" else ingredient.measure

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            shadowElevation = 3.dp,
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(modifier = Modifier
                .background(GreenParsley)
                .paint(
                    painter = rememberAsyncImagePainter(model = ingredient.image),
                    contentScale = ContentScale.FillBounds
                )
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column {
            Text(text = ingredient.food,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                maxLines = 1,
                modifier = Modifier
                    .width(80.dp))
            Text(text = if (itemQuantity !="0") "$itemQuantity $meassure" else meassure,
                color = Grey46,
                fontSize = 13.sp)
        }
    }
}



@Composable
fun RecipeContainer(image:String, name: String, ingredients: List<Ingredient>) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        //TopContainer(image =name )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
        ) {
            //DetailsContent(name = image)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            //IngredientsContainer()
        }

    }

}

@Preview
@Composable
fun TopContainerTest(){
    Surface(
        shape = RoundedCornerShape(bottomStart = DpDimensions.Dp30, bottomEnd = DpDimensions.Dp30),
        modifier = Modifier
            //.fillMaxSize()
            //.fillMaxWidth()
            .height(450.dp)
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.food),
                    contentScale = ContentScale.FillBounds
                )
            // .height(500.dp)
        ) {
            // Box(contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .padding(DpDimensions.Normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50)),
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.left_chevron_t), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(18.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50))
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.bookmark), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(22.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun DetailsContentTest(){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier,
            //.fillMaxWidth()
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier
            //.clip(RoundedCornerShape(DpDimensions.Dp30))
            .padding(vertical = DpDimensions.Normal),
            contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = DpDimensions.Normal)
                ) {
                    Text(text = "White Chocolate Covered Cherries And Two Beans",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 2,
                        textAlign = TextAlign.Center
                    )
                    Text(text = "6 ingredients",
                        modifier = Modifier
                            .padding(top = DpDimensions.Smallest),
                        color = Grey46)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DpDimensions.Normal)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.time), contentDescription = "",
                            modifier = Modifier
                                // .background(Color.White)
                                //.padding(DpDimensions.Small)
                                .size(22.dp)
                        )
                        Text(text = "234 cals")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.calories), contentDescription = "",
                            modifier = Modifier
                                // .background(Color.White)
                                //.padding(DpDimensions.Small)
                                .size(22.dp)
                        )
                        Text(text = "30 min")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Icon(tint = AppColor, painter = painterResource(id = R.drawable.spoon_and_fork), contentDescription = "",
                            modifier = Modifier
                                // .background(Color.White)
                                //.padding(DpDimensions.Small)
                                .size(22.dp)
                        )
                        Text(text = "2 serves")
                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun IngredientsContainerTest() {
    Column(modifier = Modifier
        .padding(vertical = DpDimensions.Small),
        verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Ingredients",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = DpDimensions.Dp20)){
            items(ingredients) { ingredient ->
                IngredientItemTest2(ingredient)
            }
        }
    }
}

@Preview
@Composable
fun IngredientItemTest() {

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            shadowElevation = 3.dp,
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(modifier = Modifier
                .background(RedTomato)
                .padding(DpDimensions.Normal)
                .size(80.dp)
            ){
                Image(painter = painterResource(id = R.drawable.tomato) , contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column {
            Text(text = "Tomato",
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp)
            Text(text = "1 item",
                color = Grey46,
                fontSize = 13.sp)
        }
    }

}



@Preview
@Composable
fun PrincipalTest() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopContainerTest()
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
        ) {
            DetailsContentTest()
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            IngredientsContainerTest()
        }

    }

}