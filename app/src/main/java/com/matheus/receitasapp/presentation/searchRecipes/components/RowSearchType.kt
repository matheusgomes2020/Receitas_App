package com.matheus.receitasapp.presentation.searchRecipes.components

data class RowSearchType(
    var label: String
)

val mealTypesList = listOf("BreakFast", "Dinner", "Lunch", "Snack", "TeaTime")

val cuisineTypesList = listOf("American", "Asian", "British", "Chinese", "Eastern Europe", "French", "Indian", "Italian",
    "Japanese", "Kosher", "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "South American", "South East Asian")

val dietTypesList = listOf("balanced", "high-fiber", "high-protein", "low-carb", "low-fat", "low-sodium")

val healthTypesList = listOf("alcohol-free", "dairy-free", "egg-free", "fish-free", "gluten-free", "low-sugar",
    "no-oil-added", "peanut-free", "pescatarian", "pork-free", "red-meat-free", "vegan")

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
