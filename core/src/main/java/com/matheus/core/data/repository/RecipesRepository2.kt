package com.matheus.core.data.repository

import androidx.paging.PagingSource
import com.matheus.core.domain.model.Recipe2

interface RecipesRepository2 {

    fun getRecipes(query: String): PagingSource<Int, Recipe2>

}