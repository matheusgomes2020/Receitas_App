package com.matheus.receitasapp.presentation.ingredients

import androidx.paging.PagingData
import com.matheus.MainCoroutineRule
import com.matheus.core.usecase.GetRecipesUseCase2
import com.matheus.testing.model.RecipeFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Mock
    private lateinit var getRecipesUseCase: GetRecipesUseCase2

    private lateinit var recipesViewModel: RecipesViewModel

    private val recipesFactory = RecipeFactory()

    private val pagingDataRecipes = PagingData.from(
        listOf(
            recipesFactory.create(RecipeFactory.Recipe.BakedPotatoSnack),
            recipesFactory.create(RecipeFactory.Recipe.SweetPotatoCrisps)
        )
    )

    @Before
    fun setUp() {
        recipesViewModel = RecipesViewModel(getRecipesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling recipesPaging`() =
        runTest{
            whenever(
                getRecipesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    pagingDataRecipes
                )
            )



            val result = recipesViewModel.recipesPagingDataMaster("potato")

            assertEquals(1, result.count())

        }

    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun `should throw an exception when calling to use case returns an exception`() =
        runTest {
            whenever(getRecipesUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            recipesViewModel.recipesPagingData("")
        }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

}




