package com.matheus.receitasapp.framework.di

import com.matheus.core.usecase.GetRecipesUseCase2
import com.matheus.core.usecase.GetRecipesUseCase2Impl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindRecipesUseCase2(useCase: GetRecipesUseCase2Impl): GetRecipesUseCase2

}