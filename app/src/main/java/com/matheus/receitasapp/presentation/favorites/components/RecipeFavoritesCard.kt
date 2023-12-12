package com.matheus.receitasapp.presentation.favorites.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.fontFamily3

@Composable
fun RecipeFavoritesCard(
    navController: NavController,
    recipe: RecipeRoom,
    deleteRecipe: () -> Unit
) {

    val qtd = recipe.ingredients
    val totalTime = recipe.time
    val image = recipe.image
    val label = recipe.title
    var uri: String = recipe.id
    var uriToId: String = uri.substring(uri.indexOf("_")+1)

    RecipeCard2(navController, uriToId, image, label, qtd.toInt(), totalTime, deleteRecipe)

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipeCard2(
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

        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .combinedClickable(
                    onClick = {
                        navController.navigate("${NavDestinations.RecipeDetails.RECIPE_DETAILS}/${id}")
                    },
                    onLongClick = {
                        isDeleteSheetOpen = true
                        //deleteMovie()
                    }
                ),
            elevation = 3.dp
        ) {
            Row {

                Image(painter = rememberAsyncImagePainter(
                    model = image,
                ), contentDescription = "",
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(DpDimensions.Small)),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = DpDimensions.Normal,
                                end = DpDimensions.Smallest,
                                top = DpDimensions.Normal,
                                bottom = DpDimensions.Normal)
                    ) {
                        Text(
                            modifier = Modifier
                                .width(165.dp),
                            text = label,
                            fontFamily = fontFamily3,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            maxLines = 1
                        )
                        Text(
                            text = "$qtd ingredients",
                            color = Grey46,
                            fontFamily = fontFamily3,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "$totalTime min",
                            color = Grey46,
                            fontFamily = fontFamily3,
                            fontSize = 13.sp
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .padding(
                                top = DpDimensions.Small,
                                end = 12.dp
                            )
                            .size(30.dp)
                            .clickable {
                                       deleteRecipe().let {
                                           Toast.makeText(context, "${label} removido com sucesso!!!", Toast.LENGTH_SHORT).show()
                                       }
                            },
                        painter = painterResource(id = R.drawable.bookmark_filled), contentDescription = "bookmark filled icon",
                        tint = GreenApp
                    )
                }
            }

        }


    if ( isDeleteSheetOpen ) {
        DeleteRecipeBottomSheet(
            name = label,
            bottomSheetState = bottomSheetState,
            onDismiss = { isDeleteSheetOpen = false }, onDelete = {
                deleteRecipe().let {
                    Toast.makeText(context, "${label} removido com sucesso!!!", Toast.LENGTH_SHORT).show()
                    isDeleteSheetOpen = false
                }
            },
            onCancel = {
                isDeleteSheetOpen = false
            })
    }
}