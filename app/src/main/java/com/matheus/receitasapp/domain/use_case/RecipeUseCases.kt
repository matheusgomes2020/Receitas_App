package com.matheus.receitasapp.domain.use_case

import com.matheus.receitasapp.domain.use_case.room.AddRecipe
import com.matheus.receitasapp.domain.use_case.room.DeleteRecipe
import com.matheus.receitasapp.domain.use_case.room.GetRecipe
import com.matheus.receitasapp.domain.use_case.room.GetRecipes

data class RecipeUseCases(

    val getRecipes: GetRecipes,
    val deleteRecipe: DeleteRecipe,
    val addRecipe: AddRecipe,
    val getRecipe: GetRecipe

)
