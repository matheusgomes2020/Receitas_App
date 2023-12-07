package com.matheus.receitasapp.presentation.favorites.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.matheus.receitasapp.domain.model.RecipeRoom
import com.matheus.receitasapp.presentation.home.components.RecipeCard

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

    RecipeCard(navController, uriToId, image, label, qtd.toInt(), totalTime, deleteRecipe)

}