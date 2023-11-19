package com.matheus.receitasapp.domain.use_case.get_recipe_Info

import android.util.Log
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject

class GetRecipeInfoUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    operator fun invoke( query: String ): Flow<Resource<Hit>> = flow {
        Log.d("VAILOGO", ":CHAMA USE CASE ${query} |")


        try {
            emit( Resource.Loading<Hit>() )
            val recipe = repository.getRecipeInfo( query )
            emit(Resource.Success<Hit>(recipe))
            Log.d("VAILOGO", ":SUCESSO USE CASE ${recipe} |")
        } catch(e: HttpException) {
            emit(Resource.Error<Hit>(e.localizedMessage ?: "An unexpected error occured"))
            Log.d("VAILOGO", ":erro USE CASE  |${e.localizedMessage}")

        } catch(e: IOException) {
            emit(Resource.Error<Hit>("Couldn't reach server. Check your internet connection."))
            Log.d("VAILOGO", ":EX USE CASE  |")

        }

    }
}