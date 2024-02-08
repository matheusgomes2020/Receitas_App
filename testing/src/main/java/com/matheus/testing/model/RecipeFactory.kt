package com.matheus.testing.model

import com.matheus.core.domain.model.Recipe2

class RecipeFactory {
    fun create(recipe: Recipe) = when (recipe) {
        Recipe.BakedPotatoSnack -> Recipe2(
            "Baked Potato Snack",
            "",
            "0.0",
            "5",
            "3"
        )
        Recipe.SweetPotatoCrisps -> Recipe2(
            "Sweet potato crisps",
            "",
            "0",
            "5",
            "3"
        )
    }

    sealed class Recipe {
        object BakedPotatoSnack : Recipe()
        object SweetPotatoCrisps : Recipe()
    }
}