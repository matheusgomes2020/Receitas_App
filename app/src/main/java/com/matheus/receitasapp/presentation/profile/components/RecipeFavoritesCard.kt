package com.matheus.receitasapp.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.presentation.home.components.RecipeCard
import kotlin.math.roundToInt

@Composable
fun RecipeFavoritesCard(
    navController: NavController,
    recipe: RecipeRoom,
    deleteMovie: () -> Unit
) {

    val qtd = recipe.ingredients
    val totalTime = recipe.time
    val image = recipe.image
    val label = recipe.title
    var uri: String = recipe.id
    var uriToId: String = uri.substring(uri.indexOf("_")+1)

    RecipeCard(navController, uriToId, image, label, qtd.toInt(), totalTime, deleteMovie)

}