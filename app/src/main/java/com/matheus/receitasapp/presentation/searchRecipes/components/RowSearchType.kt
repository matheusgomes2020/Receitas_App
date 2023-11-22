package com.matheus.receitasapp.presentation.searchRecipes.components

data class RowSearchType(
    var label: String
)

val types = listOf(
    RowSearchType(
        "BreakFast"
    ),
    RowSearchType(
        "Dinner"
    ),
    RowSearchType(
        "Lunch"
    ),
    RowSearchType(
        "Snack"
    ),
    RowSearchType(
        "TeaTime"
    ),
)
