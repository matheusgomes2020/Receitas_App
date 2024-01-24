package com.matheus.receitasapp.framework.di

import android.content.Context
import com.matheus.receitasapp.data.repository.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceRepository(@ApplicationContext appContext: Context) =
        PreferenceRepository(appContext)

}