package com.matheus.receitasapp.presentation.home.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.favorites.components.DeleteRecipeBottomSheet
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.fontFamily3
import kotlin.math.roundToInt

@Composable
fun RecipeHomeCard(
    navController: NavController,
    recipe: Recipe
) {

    val qtd = recipe.ingredients.size
    val totalTime = recipe.totalTime.roundToInt().toString()
    val image = recipe.image
    val label = recipe.label
    var uri: String = recipe.uri
    var uriToId: String = uri.substring(uri.indexOf("_")+1)
    Log.d("DICÇÃO", "IngredientsScreen: $uriToId")

    RecipeCard(navController, uriToId, image, label, qtd, totalTime, {})

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipeCard(
    navController: NavController,
    id: String,
    image: String,
    label: String,
    qtd: Int,
    totalTime: String,
    deleteRecipe: () -> Unit
) {

    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState()
    var isDeleteSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
                .combinedClickable(
                    onClick = {
                        navController.navigate("${NavDestinations.RecipeDetails.RECIPE_DETAILS}/${id}")
                    },
                    onLongClick = {
                        isDeleteSheetOpen
                        //deleteMovie()
                    }
                )

        ) {

            Box( modifier = Modifier
                .width(170.dp)
                .height(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    ),
                )
                .paint(
                    painter = rememberAsyncImagePainter(model = image),
                    contentScale = ContentScale.Crop
                ))
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 1.dp, vertical = DpDimensions.Small7)
        ) {
            Text(
                modifier = Modifier
                    .width(170.dp),
                text = label,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontFamily = fontFamily3,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                maxLines = 1
            )
            Text(
                text = "$qtd ingredients - $totalTime min",
                color = if (isSystemInDarkTheme()) Color.White else Grey46,
                fontFamily = fontFamily3,
                fontSize = 13.sp
            )
        }
    }
    if ( isDeleteSheetOpen ) {
        DeleteRecipeBottomSheet(
            name = label,
            bottomSheetState = bottomSheetState,
            onDismiss = { isDeleteSheetOpen = false }, onDelete = {
                deleteRecipe().let {
                    Toast.makeText(context, "${label} removido com sucesso!!!", Toast.LENGTH_SHORT).show()
                }
            },
            onCancel = {
                isDeleteSheetOpen = false
            })
    }
}
