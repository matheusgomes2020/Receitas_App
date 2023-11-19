package com.matheus.receitasapp.domain.use_case.get_recipes

import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    operator fun invoke( query: String ): Flow<Resource<List<Hit>>> = flow {

        try {
            emit( Resource.Loading<List<Hit>>() )
            val recipes = repository.getRecipes( query )
            emit(Resource.Success<List<Hit>>(recipes))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Hit>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Hit>>("Couldn't reach server. Check your internet connection."))
        }

    }
}