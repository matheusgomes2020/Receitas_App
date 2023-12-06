package com.matheus.receitasapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_tbl")
data class RecipeRoom(
    @PrimaryKey
    var id: String,
    val title: String,
    val image: String,
    val ingredients: String,
    val timestamp: Long,
    val time: String,
)

class InvalidRecipeException(message: String): Exception(message)
