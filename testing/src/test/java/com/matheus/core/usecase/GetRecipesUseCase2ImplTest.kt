package com.matheus.core.usecase

import androidx.paging.PagingConfig
import com.matheus.MainCoroutineRule
import com.matheus.core.data.repository.RecipesRepository2
import com.matheus.testing.model.RecipeFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRecipesUseCase2ImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: RecipesRepository2

    private lateinit var getRecipesUseCase: GetRecipesUseCase2

    private val recipe = RecipeFactory().create(RecipeFactory.Recipe.SweetPotatoCrisps)

    private val fakePagingSource = com.matheus.testing.pagingsource.PagingSourceFactory().create(listOf(recipe))

    @Before
    fun setUp() {
        getRecipesUseCase = GetRecipesUseCase2Impl(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate flow paging data creation when invoke is called`() =
        runTest{
            whenever(repository.getRecipes(""))
                .thenReturn(fakePagingSource)


            val result = getRecipesUseCase
                .invoke(GetRecipesUseCase2.GetRecipesParams("", PagingConfig(20)))

            verify(repository).getRecipes("")

            assertNotNull(result.first())

        }


}

