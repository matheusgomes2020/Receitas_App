package com.matheus.receitasapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matheus.receitasapp.domain.model.RecipeRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes_tbl")
    fun getRecipes(): Flow<List<RecipeRoom>>

    @Query("SELECT * FROM recipes_tbl WHERE id = :id")
     fun getRecipeById(id: String): RecipeRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertRecipe( recipeRoom: RecipeRoom )

    @Delete
     fun deleteRecipe( recipeRoom: RecipeRoom )

}