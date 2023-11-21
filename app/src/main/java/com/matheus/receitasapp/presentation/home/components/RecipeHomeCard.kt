package com.matheus.receitasapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme
import com.matheus.receitasapp.ui.theme.RoyalBlue65
import com.matheus.receitasapp.ui.theme.fontFamily3
import kotlin.math.roundToInt

@Composable
fun RecipeHomeCard(
    navController: NavController,
    recipe: Recipe
) {

    val qtd = recipe.ingredients.size
    val totalTime = recipe.totalTime.roundToInt().toString()
    var s: String = recipe.uri
    var s1: String = s.substring(s.indexOf("_")+1)

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            color = RoyalBlue65,
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .clickable {
                    navController.navigate("${NavDestinations.RecipeDetails.RECIPE_DETAILS}/${s1}")
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = recipe.image),
                    contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(170.dp)
//                    .width(200.dp)
//                    .height(300.dp),
                    .height(250.dp),
            )

        }
        Column(
            modifier = Modifier
                .padding( horizontal = 1.dp, vertical = DpDimensions.Small7 )
        ) {
            Text(
                modifier = Modifier
                    .width(170.dp),
                text = recipe.label,
                fontFamily = fontFamily3,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                maxLines = 1
            )
            Text(text = "$qtd ingredients - $totalTime min",
                color = Grey46,
                fontFamily = fontFamily3,
                fontSize = 13.sp)
        }
    }

}

@Preview
@Composable
fun aa() {
    ReceitasAppTheme {
      Column(
          modifier = Modifier
              .fillMaxWidth()
              .background(Color.White)

      ) {
          LazyRow(
              horizontalArrangement = Arrangement.spacedBy(DpDimensions.Normal),
              modifier = Modifier,
              contentPadding = PaddingValues(horizontal = DpDimensions.Dp20, vertical = DpDimensions.Dp20)
          ){
              items(3) {
                 // TopCard()
              }
          }
      }
    }
}
