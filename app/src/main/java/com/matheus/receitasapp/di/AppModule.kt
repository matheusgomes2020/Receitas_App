package com.matheus.receitasapp.di

import android.app.Application
import androidx.room.Room
import com.matheus.receitasapp.common.Constants.BASE_URL
import com.matheus.receitasapp.data.data_source.RecipeDatabase
import com.matheus.receitasapp.data.remote.RecipeApi
import com.matheus.receitasapp.data.repository.RecipesRepositoryImpl
import com.matheus.receitasapp.data.repository.RoomRepositoryImpl
import com.matheus.receitasapp.domain.repository.RecipesRepository
import com.matheus.receitasapp.domain.repository.RoomRepository
import com.matheus.receitasapp.domain.use_case.RecipeUseCases
import com.matheus.receitasapp.domain.use_case.room.AddRecipe
import com.matheus.receitasapp.domain.use_case.room.DeleteRecipe
import com.matheus.receitasapp.domain.use_case.room.GetRecipe
import com.matheus.receitasapp.domain.use_case.room.GetRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Singleton
    @Provides
    fun providesRecipeApi(): RecipeApi {
        return Retrofit.Builder()
            .baseUrl( BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( RecipeApi::class.java )
    }

    @Singleton
    @Provides
    fun provideRecipesRepository( api: RecipeApi ): RecipesRepository {
        return RecipesRepositoryImpl( api )
    }

    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            RecipeDatabase.DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: RecipeDatabase): RoomRepository {
        return RoomRepositoryImpl(db.recipeDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: RoomRepository): RecipeUseCases {
        return RecipeUseCases(
            getRecipes = GetRecipes(repository),
            deleteRecipe = DeleteRecipe(repository),
            addRecipe = AddRecipe(repository),
            getRecipe = GetRecipe(repository)
        )
    }

}