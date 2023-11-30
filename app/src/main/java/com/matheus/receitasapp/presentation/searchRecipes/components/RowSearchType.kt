package com.matheus.receitasapp.presentation.searchRecipes.components

data class RowSearchType(
    var label: String
)

val mealTypesList = listOf("BreakFast", "Dinner", "Lunch", "Snack", "TeaTime")

val cuisineTypesList = listOf("American", "Asian", "British", "Chinese", "French")

val dietTypesList = listOf("balanced", "high-fiber", "low-carb", "low-fat", "low-sodium"

)

val mealTypes = listOf(
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

val cuisineTypes = listOf(
    RowSearchType(
        "American"
    ),
    RowSearchType(
        "Asian"
    ),
    RowSearchType(
        "British"
    ),
    RowSearchType(
        "Chinese"
    ),
    RowSearchType(
        "French"
    )
)

val dietTypes = listOf(
    RowSearchType(
        "balanced"
    ),
    RowSearchType(
        "low-carb"
    ),
    RowSearchType(
        "low-fat"
    ),
    RowSearchType(
        "low-fat"
    ),
    RowSearchType(
        "low-sodium"
    )
)
