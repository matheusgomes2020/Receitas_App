package com.matheus.receitasapp.presentation.recipe_detail

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.common.ShimmerRecipeDetail
import com.matheus.receitasapp.data.remote.dto.Digest
import com.matheus.receitasapp.data.remote.dto.Ingredient
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.presentation.recipe_detail.components.IngredientsDetail
import com.matheus.receitasapp.presentation.recipe_detail.components.ingredients
import com.matheus.receitasapp.presentation.recipes.AddRecipeEvent
import com.matheus.receitasapp.presentation.recipes.AddRoomRecipeViewModel
import com.matheus.receitasapp.presentation.recipes.GetRecipesViewModel
import com.matheus.receitasapp.presentation.recipes.RecipesEvent
import com.matheus.receitasapp.ui.theme.AppColor
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.GreenParsley
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.RedTomato
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.roundToInt

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    addRecipeViewModel: AddRoomRecipeViewModel = hiltViewModel(),
    getRecipesViewModel: GetRecipesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val stateRecipesRoom = getRecipesViewModel.state.value

    val context = LocalContext.current


   // val idState =
    addRecipeViewModel.onEvent(AddRecipeEvent.EnteredTitle(uiState.label.toString()))
    addRecipeViewModel.onEvent(AddRecipeEvent.EnteredTime(uiState.totalTime.toString()))
    addRecipeViewModel.onEvent(AddRecipeEvent.EnteredImage(uiState.image.toString()))
    addRecipeViewModel.onEvent(AddRecipeEvent.EnteredIngredients(uiState.ingredients.size.toString()))
    addRecipeViewModel.onEvent(AddRecipeEvent.EnteredId(uiState.id.toString()))

//    val titleState = addRecipeViewModel.recipeTitle.value
//    val timeState = addRecipeViewModel.recipeTime.value
//    val ingredientsState = addRecipeViewModel.recipeIngredients.value
//    val imageState = addRecipeViewModel.recipeImage.value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    var recipeRoom = RecipeRoom(
        image = "",
        ingredients = "",
        id = "",
        time = "",
        timestamp = 0,
        title = uiState.label.toString()
    )

    LaunchedEffect(key1 = true) {
        addRecipeViewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddRoomRecipeViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddRoomRecipeViewModel.UiEvent.SaveRecipe -> {
                }
            }
        }
    }


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

            ShimmerRecipeDetail()
    }
    else{
        var uri: String = uiState.id!!
        var uriToId: String = uri.substring(uri.indexOf("_")+1)


        stateRecipesRoom.recipes.let { recipeList ->

            var isFavorite = false
            var list = recipeList.filter { recipe ->
                var uri2: String = recipe.id
                var uriToId2: String = uri2.substring(uri2.indexOf("_")+1)
                uriToId2 == uriToId
            }
            Log.d("MERCADOLIVRE", "RecipeDetailScreen: \n$recipeList \n$list | \n$uriToId")

            if (!list.isNullOrEmpty()){
                isFavorite = true
                recipeRoom = list[0]
                Log.d("MERCADOLIVRE", "Recipe ROOM???!NULL: $recipeRoom")

            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                uiState.image?.let { TopContainer(context = context,recipeRoom = recipeRoom, isFavorite = isFavorite, image = it, navController = navController, addRecipeViewModel = addRecipeViewModel, getRecipesViewModel = getRecipesViewModel) }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
                ) {
                    uiState.label?.let { uiState.calories?.let { it1 -> uiState.totalTime?.let { it2 -> DetailsContent( name = it, ingredientsQuantity = uiState.ingredients.size, calories = it1, totalTime = it2) } } }
                    Spacer(modifier = Modifier.height(DpDimensions.Small))
                    IngredientsContainer( uiState.ingredients)
                    Infos( uiState.calories!!,uiState.digest )
                    var aa = listOf(
                        "On English muffin half, layer a tomato slice, a mozzarella slice, and half of the basil.",
                        "Repeat layers.",
                        "In a small bowl, combine olive oil, dash salt, and dash pepper.",
                        "Drizzle over top.",
                        "If desired, top with grape tomato halves."
                    )
                    InstructionsContainer(instructions = aa)
                }

            }
        }
    }

}
@Composable
fun TopContainer(
    recipeRoom: RecipeRoom,
    isFavorite: Boolean,
    image: String,
    navController: NavController,
    addRecipeViewModel: AddRoomRecipeViewModel,
    getRecipesViewModel: GetRecipesViewModel,
    context: Context
){
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
                    var icon = if (isFavorite) R.drawable.bookmark_filled else R.drawable.bookmark
                    Icon(tint = GreenApp, painter =
                    painterResource(
                        id = icon), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(22.dp)
                            .clickable {
                                if (isFavorite)
                                    getRecipesViewModel
                                        .onEvent(RecipesEvent.DeleteRecipe(recipeRoom))
                                        .let {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "${recipeRoom.title} removida dos favoritos!!!",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
                                else addRecipeViewModel
                                    .onEvent(AddRecipeEvent.SaveRecipe)
                                    .let {
                                        Toast
                                            .makeText(
                                                context,
                                                "${recipeRoom.title} salva nos favoritos!!!",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }

                            }
                    )
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
fun InstructionsContainer( instructions: List<String>) {
    Column(
        modifier = Modifier
            .padding(vertical = DpDimensions.Normal)
            //.clip(RoundedCornerShape(DpDimensions.Normal))
    ) {
        Text(text = "Instructions",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(DpDimensions.Normal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = DpDimensions.Normal
                )
        ) {
            var count = 0
            LazyColumn(modifier = Modifier
                .height(300.dp)
                .padding(DpDimensions.Small)){
                items(instructions) {
                    count ++
                    Text(text = "$count. $it" )
                }
            }
        }
    }
}

@Composable
fun InstructionItem(){
    
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Infos(calories: String,digest: List<Digest>){

    val calories = calories
    var carbohydrates = "Not informed"
    var fat = "Not informed"
    var sodium = "Not informed"
    var protein = "Not informed"
    var fiber = "Not informed"
    var sugar = "Not informed"
    var cholesterol = "Not informed"
    var saturated = "Not informed"
    var trans = "Not informed"
    var polyunsaturated = "Not informed"
    var monounsaturated = "Not informed"

    var fats = digest.filter { it.label == "Fat" }
    var carbs = digest.filter { it.label == "Carbs" }

    for (i in fats) {
        for (c in i.sub){
            if (c.label == "Saturated") {
                saturated = "${c.total.roundToInt()} g"
            }
            if (c.label == "Trans") {
                trans = "${c.total.roundToInt()} g"
            }
            if (c.label == "Polyunsaturated") {
                polyunsaturated = "${c.total.roundToInt()} g"
            }
            if (c.label == "Monounsaturated") {
                monounsaturated = "${c.total.roundToInt()} g"
            }
        }
    }

    for (i in carbs) {
        for (c in i.sub){
            if (c.label == "Fiber") {
                fiber = "${c.total.roundToInt()} mg"
            }
            if (c.label == "Sugars") {
                sugar = "${c.total.roundToInt()} g"
            }
        }
    }


    for (i in digest) {
        if (i.label == "Fat") {
            fat = "${i.total.roundToInt()} g"
        }
        if (i.label == "Carbs") {
            carbohydrates = "${i.total.roundToInt()} g"
        }
        if (i.label == "Protein") {
            protein = "${i.total.roundToInt()} g"
        }
        if (i.label == "Sodium") {
            sodium = "${i.total.roundToInt()} mg"
        }
        if (i.label == "Cholesterol") {
            cholesterol = "${i.total.roundToInt()} mg"
        }
    }

    Column {
        Text(text = "Nutritional information's",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val donutChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice("Carbs", 50f, Color(0xFF5F0A87)),
                    PieChartData.Slice("Protein", 30f, Color(0xFF20BF55)),
                    PieChartData.Slice("Fat", 20f,  Color(0xFFEC9F05)),
                ),
                plotType = PlotType.Donut
            )

            val donutChartConfig = PieChartConfig(
                isAnimationEnable = true,
                showSliceLabels = true,
                labelVisible = true,
                //percentageFontSize = 42.sp,
                strokeWidth = 120f,
                //percentColor = Color.Black,
                activeSliceAlpha = .9f,
            )

            DonutPieChart(
                modifier = Modifier
                    .size(250.dp)
                    .padding(DpDimensions.Normal),
                donutChartData,
                donutChartConfig
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RowInfo(color = 0xFF5F0A87, text = "Carbs")
                Spacer(modifier = Modifier.width(DpDimensions.Small))
                RowInfo(color = 0xFF20BF55, text = "Protein")
                Spacer(modifier = Modifier.width(DpDimensions.Small))
                RowInfo(color = 0xFFEC9F05, text = "Fat")
            }

            androidx.compose.material.Card(
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = DpDimensions.Normal
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(
                            vertical = DpDimensions.Small,
                            horizontal = DpDimensions.Normal)

                ) {
                    Text(text = "Calories",
                        fontWeight = FontWeight.Bold)
                    Text(text = "$calories kcal",
                        fontWeight = FontWeight.Bold)
                }
            }
            androidx.compose.material.Card(
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 1.dp
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = DpDimensions.Small),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    RowInfoDetails(fontWeight = FontWeight.Bold, label = "Carbohydrates", unit = carbohydrates)
                    Column(
                        modifier = Modifier
                            .padding(horizontal = DpDimensions.Small)
                    ) {
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Fiber", unit = fiber)
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Sugar", unit = sugar)
                    }
                    RowInfoDetails(fontWeight = FontWeight.Bold,label = "Protein", unit = protein)
                    RowInfoDetails(fontWeight = FontWeight.Bold,label = "Fat", unit = fat)
                    Column(
                        modifier = Modifier
                            .padding(horizontal = DpDimensions.Small)
                    ) {
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Saturated fat", unit = saturated)
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Mono unsaturated fat", unit = monounsaturated)
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "poly unsaturated fat", unit = polyunsaturated)
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Trans fat", unit = trans)
                        RowInfoDetails(fontWeight = FontWeight.Normal, label = "Cholesterol", unit = cholesterol)
                    }
                    RowInfoDetails(fontWeight = FontWeight.Bold,label = "Sodium", unit = sodium)
                }
            }
        }
    }
}

@Composable
private fun RowInfoDetails(fontWeight: FontWeight,label: String, unit: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DpDimensions.Normal)
    ) {
        Text(
            text = label,
            fontWeight = fontWeight
        )
        Text(
            text = unit,
            fontWeight = fontWeight
        )
    }
}

@Composable
private fun RowInfo(color: Long, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(DpDimensions.Normal))
                .background(Color(color))
        )
        Text(
            modifier = Modifier
                .padding(start = DpDimensions.Smallest),
            text = text,
            fontWeight = FontWeight.SemiBold,
            color = DarkGrey11
        )
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