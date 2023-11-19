package com.matheus.receitasapp.presentation.searchRecipes

import com.matheus.receitasapp.data.remote.dto.Hit

data class HitListState (
    val isLoading: Boolean = false,
    val recipes: List<Hit> = emptyList(),
    val error: String = ""
)