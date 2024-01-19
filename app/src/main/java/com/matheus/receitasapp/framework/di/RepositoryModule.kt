package com.matheus.receitasapp.framework.di

import com.matheus.core.data.repository.RecipesRemoteDataSource
import com.matheus.core.data.repository.RecipesRepository2
import com.matheus.receitasapp.framework.RecipesRepositoryImpl2
import com.matheus.receitasapp.framework.network.response.DataWrapperResponse
import com.matheus.receitasapp.framework.remote.RetrofitRecipesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRecipesRepository(repository: RecipesRepositoryImpl2) : RecipesRepository2

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitRecipesDataSource
    ) : RecipesRemoteDataSource<DataWrapperResponse>

}