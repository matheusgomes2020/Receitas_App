package com.matheus.receitasapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.recipe_detail.components.ItemDetail


@Composable
fun TopRecipeItem(
    navController: NavController,
    ingredient: ItemDetail,
            ) {
    Surface(
        shape = RoundedCornerShape(DpDimensions.Small),
        shadowElevation = 3.dp,
        modifier = Modifier
            .size(100.dp)
            .clickable {
                navController.navigate("${NavDestinations.TopRecipes.TOP_RECIPES}/${ingredient.title}")
            }
    ) {
        Box(
           // horizontalAlignment = Alignment.End,
            modifier = Modifier
            .background(ingredient.backgroundColor)

//            .paint(
//                painter = painterResource(id = R.drawable.pizza),
//                contentScale = ContentScale.FillBounds
//            ),
           // contentAlignment = Alignment.BottomStart
        ) { Column(
            modifier = Modifier
                .padding(top = 3.dp, start = 25.dp),
        ) {
            Image(painter = painterResource(ingredient.image), contentDescription ="",
                modifier = Modifier
                    .size(70.dp)

            )
        }

            Column(
                modifier = Modifier.padding(start = DpDimensions.Small, top = 73.dp)
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = ingredient.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}