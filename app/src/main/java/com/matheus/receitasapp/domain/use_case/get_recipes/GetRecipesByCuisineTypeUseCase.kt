package com.matheus.receitasapp.domain.use_case.get_recipes



import android.util.Log
import com.matheus.receitasapp.common.Resource
import com.matheus.receitasapp.data.remote.dto.Hit
import com.matheus.receitasapp.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesByCuisineTypeUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    operator fun invoke( query: String ): Flow<Resource<List<Hit>>> = flow {
        Log.d("ISSO", "GetRecipesByCuisineTypeUseCase: is $query")


        try {
            emit( Resource.Loading<List<Hit>>() )
            val recipes = repository.getRecipesByCuisineType( query )
            Log.d("ISSO", "GetRecipesByCuisineTypeUseCase: is $recipes")

            emit(Resource.Success<List<Hit>>(recipes))
        } catch(e: HttpException) {
            Log.d("ISSO", "GetRecipesByCuisineTypeUseCase: is ${e.localizedMessage}")

            emit(Resource.Error<List<Hit>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            Log.d("ISSO", "GetRecipesByCuisineTypeUseCase: is ERRROOO")

            emit(Resource.Error<List<Hit>>("Couldn't reach server. Check your internet connection."))
        }

    }
}