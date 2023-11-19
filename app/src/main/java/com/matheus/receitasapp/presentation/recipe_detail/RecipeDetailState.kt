package com.matheus.receitasapp.presentation.recipe_detail

import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.data.remote.dto.Recipe

data class RecipeDetailState (
    val isLoading: Boolean = false,
    val recipe: Hit? = null,
    val error: String = ""
)
