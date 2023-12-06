package com.matheus.receitasapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matheus.receitasapp.domain.model.RecipeRoom

@Database(
    entities = [ RecipeRoom::class ],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase: RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object {

        const val DATABASE_NAME = "recipes_db"

    }

}