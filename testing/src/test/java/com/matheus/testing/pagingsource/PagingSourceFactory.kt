package com.matheus.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matheus.core.domain.model.Recipe2

class PagingSourceFactory {

    fun create(recipes: List<Recipe2>) = object: PagingSource<Int, Recipe2>() {
        override fun getRefreshKey(state: PagingState<Int, Recipe2>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe2> {
            return LoadResult.Page(
                data = recipes,
                prevKey = null,
                nextKey = 20
            )
        }

    }

}