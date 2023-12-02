package com.matheus.receitasapp.domain.use_case.search_recipes

import android.health.connect.datatypes.MealType
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchRecipesUseCaseByHealth @Inject constructor(
    private val repository: RecipesRepository
) {

    operator fun invoke( query: String, health: String ): Flow<Resource<List<Hit>>> = flow {

        try {
            emit( Resource.Loading<List<Hit>>() )
            val recipes = repository.searchRecipesByHealth( query, health )
            emit(Resource.Success<List<Hit>>(recipes))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Hit>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Hit>>("Couldn't reach server. Check your internet connection."))
        }

    }
}