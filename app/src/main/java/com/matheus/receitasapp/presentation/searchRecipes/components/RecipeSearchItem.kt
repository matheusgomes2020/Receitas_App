package com.matheus.receitasapp.presentation.searchRecipes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.fontFamily2
import com.matheus.receitasapp.ui.theme.fontFamily3
import kotlin.math.roundToInt

@Composable
fun RecipeSearchItem(navController: NavController, recipe: Recipe) {
    val totalTime = recipe.totalTime.roundToInt().toString()
    val calories = recipe.calories.roundToInt().toString()
    var s: String = recipe.uri
    var s1: String = s.substring(s.indexOf("_")+1)
    Surface(
        modifier = Modifier
            .clickable {
                navController
                    .navigate(NavDestinations.RecipeDetails.RECIPE_DETAILS + "/${s1}")
            }
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(DpDimensions.Smallest),
    ) {
        Box(
            modifier = Modifier
                .padding(DpDimensions.Smallest)
                .background(Color.White),
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