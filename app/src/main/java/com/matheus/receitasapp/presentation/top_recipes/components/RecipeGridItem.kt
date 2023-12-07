package com.matheus.receitasapp.presentation.top_recipes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
@Composable
fun RecipeGridItem ( navController: NavController, recipe: Recipe ) {

    var uri: String = recipe.uri
    var uriToUrl: String = uri.substring(uri.indexOf("_")+1)

    Surface(
        shape = RoundedCornerShape(DpDimensions.Small),
        modifier = Modifier
            .width(100.dp)
            .height(250.dp),
        onClick = {
            navController.navigate(NavDestinations.RecipeDetails.RECIPE_DETAILS + "/${uriToUrl}")
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(model = recipe.image),
                    contentScale = ContentScale.Crop
                )
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    ),
                )
                .clip(RoundedCornerShape(DpDimensions.Small)),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(DpDimensions.Small)
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = recipe.label,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}