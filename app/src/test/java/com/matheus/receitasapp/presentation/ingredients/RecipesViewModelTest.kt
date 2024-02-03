package com.matheus.receitasapp.presentation.ingredients

import androidx.paging.PagingData
import com.matheus.core.domain.model.Recipe2
import com.matheus.core.usecase.GetRecipesUseCase2
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipesViewModelTest {

    @ExperimentalCoroutinesApi
    val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var getRecipesUseCase: GetRecipesUseCase2

    private lateinit var recipesViewModel: RecipesViewModel

    private val pagingDataRecipes = PagingData.from(
        listOf(
            Recipe2(
                "Baked Potato Snack",
                "https://edamam-product-images.s3.amazonaws.com/web-img/c03/c03870c0284bdb80902ce95f24187714.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEIP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJIMEYCIQCkpkpf3hLhIl20jKk%2FPxGnKJllL3RqV%2BQ5pNi17GDv6QIhAILLTuwQDQpCcAKevoUvOAorLQjPLmYNAFqqW3vlZEj2KrkFCEsQABoMMTg3MDE3MTUwOTg2IgxvJ9NMhTgHuFNnNOsqlgX5cFrk8mU6bhoWIkoj7mk4BJagpJ0gQvUDyv5pIXeAvNqCCCdG72CO8MyCpdrS69%2F1WQjc08SyZBgAZPuLtARM7KQsP8JlBxk5c7e6BK5uGP8XbRvirfP5h1dH%2BlwpZ9%2Fg6Eume91Z0%2Bt2KrnTNCpnHs1Bsl0s85oxomZnuNgaULlGRPUrcF%2BX%2BonZqIEWJp44a4TXIXBNswXg90sY121uM8SPO8%2FGx8OJKpToJDBDg7ru73lKxNj4d7Lml9%2F2k4B%2BaOHNPoY9TFI55KfVglb0WWWA%2B95y8%2F7yRMcprsWXx668BNTTrDGa8oNuKWYd93nhnjZga%2BxJAPlJyy0aBYh%2BPKjGGflp0jD%2BDqdIxiyBUAyb1Shg%2BPKmaeLBCIXo%2BS9djOiYZfT2L8Vua%2FIQzlwfHzCCGlwLv5NnMp0NdzUWQ4TVGAi3KSC3g96x3LPZJk2vC7OGP8htu1A3VlyzAcjsaC3Fc6RWLSUXciPmd%2BHikCWob4QICbWc5%2FfcNOR%2F%2FbJcK3qhA7z3vFTs7LIi5%2BrkBbA2CzKW8YVXxRpKhoNtsxre8WlGmzMxG%2B2NQuL%2FnEIocg6d1vQ9Y%2FvE9B%2Btxi0AoyWDBHlRUk%2FySe5sO9z3h%2Bdr8amz6v%2BYrUK1TBTvK87T%2FuUyRDDe9KeGJMv9vCwsG3RUuPWVtW3zgJBV9BQtG1PbXDslsjyXaUDUZN0PsJmNCdmqYT0lVXwXisySDWBwm8aX%2Fki1lrUiwjF9TdUYxGL2D1zqAJnlTaRYXOe08KRheEt4VMn%2FAC4eQMVMmeTAFXoZYtSwLgD%2FepNdu5TQ3saFkYeLV%2F9bxcPhgaZeMRNGstyyadGH26zzW1uTH5iaiztSLkqGIgCZ8%2FE7rJfDZNv%2BJfdsDTCNve%2BtBjqwAT5R3xwAmCUwBlt2uE5i69BvDD3Z8DAVo88xkcRofWIhcImPDEHrNKvjbGR50xehG0BBJnZryQb%2BwBIrTTEtblk300bWlZltSEYL1jcS2U%2B2aCdN%2BPRE1CNBixzzAaln4lFFMUqoENbjGZjW1I1zU5uP2go23NinGtgrY2YK0IfjoY5FVCW0%2Fx%2BSpHarxlLVH%2Fq2owhJR8CUiN8y71usFP%2FWz4GrmvX3rJ%2Bl4292g9QM&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240201T195154Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFLMSRXZNR%2F20240201%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=eaf1cc314f5867c69f2c69d99e3cf46d03086af1ae060d6b5d7eca8d16bc5fcd",
                "0.0",
                "5",
                "5"
            ),
            Recipe2(
                "Sweet potato crisps",
                "https://edamam-product-images.s3.amazonaws.com/web-img/680/680525794659eec4b5ba4ad54ef10cc5.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEIP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJIMEYCIQCkpkpf3hLhIl20jKk%2FPxGnKJllL3RqV%2BQ5pNi17GDv6QIhAILLTuwQDQpCcAKevoUvOAorLQjPLmYNAFqqW3vlZEj2KrkFCEsQABoMMTg3MDE3MTUwOTg2IgxvJ9NMhTgHuFNnNOsqlgX5cFrk8mU6bhoWIkoj7mk4BJagpJ0gQvUDyv5pIXeAvNqCCCdG72CO8MyCpdrS69%2F1WQjc08SyZBgAZPuLtARM7KQsP8JlBxk5c7e6BK5uGP8XbRvirfP5h1dH%2BlwpZ9%2Fg6Eume91Z0%2Bt2KrnTNCpnHs1Bsl0s85oxomZnuNgaULlGRPUrcF%2BX%2BonZqIEWJp44a4TXIXBNswXg90sY121uM8SPO8%2FGx8OJKpToJDBDg7ru73lKxNj4d7Lml9%2F2k4B%2BaOHNPoY9TFI55KfVglb0WWWA%2B95y8%2F7yRMcprsWXx668BNTTrDGa8oNuKWYd93nhnjZga%2BxJAPlJyy0aBYh%2BPKjGGflp0jD%2BDqdIxiyBUAyb1Shg%2BPKmaeLBCIXo%2BS9djOiYZfT2L8Vua%2FIQzlwfHzCCGlwLv5NnMp0NdzUWQ4TVGAi3KSC3g96x3LPZJk2vC7OGP8htu1A3VlyzAcjsaC3Fc6RWLSUXciPmd%2BHikCWob4QICbWc5%2FfcNOR%2F%2FbJcK3qhA7z3vFTs7LIi5%2BrkBbA2CzKW8YVXxRpKhoNtsxre8WlGmzMxG%2B2NQuL%2FnEIocg6d1vQ9Y%2FvE9B%2Btxi0AoyWDBHlRUk%2FySe5sO9z3h%2Bdr8amz6v%2BYrUK1TBTvK87T%2FuUyRDDe9KeGJMv9vCwsG3RUuPWVtW3zgJBV9BQtG1PbXDslsjyXaUDUZN0PsJmNCdmqYT0lVXwXisySDWBwm8aX%2Fki1lrUiwjF9TdUYxGL2D1zqAJnlTaRYXOe08KRheEt4VMn%2FAC4eQMVMmeTAFXoZYtSwLgD%2FepNdu5TQ3saFkYeLV%2F9bxcPhgaZeMRNGstyyadGH26zzW1uTH5iaiztSLkqGIgCZ8%2FE7rJfDZNv%2BJfdsDTCNve%2BtBjqwAT5R3xwAmCUwBlt2uE5i69BvDD3Z8DAVo88xkcRofWIhcImPDEHrNKvjbGR50xehG0BBJnZryQb%2BwBIrTTEtblk300bWlZltSEYL1jcS2U%2B2aCdN%2BPRE1CNBixzzAaln4lFFMUqoENbjGZjW1I1zU5uP2go23NinGtgrY2YK0IfjoY5FVCW0%2Fx%2BSpHarxlLVH%2Fq2owhJR8CUiN8y71usFP%2FWz4GrmvX3rJ%2Bl4292g9QM&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240201T195348Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFLMSRXZNR%2F20240201%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=ddc5927f0b59820ac4afc4a48ffb0b3c7639b79a2f1db98075e0af18507dd779",
                "0",
                "5",
                "5"
            )
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain((testDispatcher))
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

            assertEquals(0, recipesViewModel)


            val result = recipesViewModel.recipesState.drop(1).first()

            assertEquals(2, 1)

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
        testDispatcher
    }

}





