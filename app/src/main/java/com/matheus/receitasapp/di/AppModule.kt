package com.matheus.receitasapp.di

import com.matheus.receitasapp.common.Constants.BASE_URL
import com.matheus.receitasapp.data.remote.RecipeApi
import com.matheus.receitasapp.data.repository.RecipesRepositoryImpl
import com.matheus.receitasapp.domain.repository.RecipesRepository
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
}