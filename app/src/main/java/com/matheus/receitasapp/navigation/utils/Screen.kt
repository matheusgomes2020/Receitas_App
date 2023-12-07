package com.matheus.receitasapp.navigation.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.matheus.receitasapp.R

sealed class Screen( val route: String, @StringRes val label: Int, @DrawableRes val icon: Int ) {
    object Home: Screen(HOME, R.string.home, R.drawable.home)
    object Ingredients: Screen(INGREDIENTS, R.string.ingredients, R.drawable.grid)
    object Favorites: Screen(FAVORITES, R.string.favorites, R.drawable.bookmarks)

    companion object {
        const val HOME = "home"
        const val INGREDIENTS = "ingredients"
        const val FAVORITES = "favorites"
    }
}

val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Ingredients,
    Screen.Favorites
)
