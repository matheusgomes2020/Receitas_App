package com.matheus.receitasapp.presentation.recipe_detail.components

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.matheus.receitasapp.R
import com.matheus.receitasapp.ui.theme.BlueMilk
import com.matheus.receitasapp.ui.theme.BrownFlour
import com.matheus.receitasapp.ui.theme.GreenParsley
import com.matheus.receitasapp.ui.theme.Orange53
import com.matheus.receitasapp.ui.theme.OrangeBurguer
import com.matheus.receitasapp.ui.theme.RedTomato
import com.matheus.receitasapp.ui.theme.SkinEgg
import com.matheus.receitasapp.ui.theme.YellowCheese
import com.matheus.receitasapp.ui.theme.YellowPasta

data class IngredientsDetail(
    val title: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color
)

val ingredients = listOf(
    IngredientsDetail(
        title = "Pizza",
        image = R.drawable.pizza,
        backgroundColor = Orange53
    ),
    IngredientsDetail(
        title = "Pasta",
        image = R.drawable.pasta,
        backgroundColor = YellowCheese
    ),IngredientsDetail(
        title = "Burger",
    image = R.drawable.hamburger,
    backgroundColor = OrangeBurguer
    ),
    IngredientsDetail(
        title = "Sushi",
        image = R.drawable.sushi,
        backgroundColor = RedTomato
    ),
    IngredientsDetail(
        title = "Taco",
        image = R.drawable.wrap,
        backgroundColor = SkinEgg
    ),
    IngredientsDetail(
        title = "Hot Dog",
        image = R.drawable.hot_dog,
        backgroundColor = YellowCheese
    ),
    IngredientsDetail(
        title = "Croissant",
        image = R.drawable.croissant,
        backgroundColor = YellowCheese
    ),
    IngredientsDetail(
        title = "Tofu",
        image = R.drawable.tofu,
        backgroundColor = BrownFlour
    ),
    IngredientsDetail(
        title = "Ramen",
        image = R.drawable.ramen,
        backgroundColor = BlueMilk
    ),
    IngredientsDetail(
        title = "Ravioli",
        image = R.drawable.ravioli,
        backgroundColor = YellowCheese
    ),
    IngredientsDetail(
        title = "Tortillas",
        image = R.drawable.tortillas,
        backgroundColor = BrownFlour
    ),
    IngredientsDetail(
        title = "Brigadeiro",
        image = R.drawable.brigadeiro,
        backgroundColor = GreenParsley
    ),IngredientsDetail(
        title = "Ice cream",
        image = R.drawable.ice_cream,
        backgroundColor = GreenParsley
    )
)

val pizza = IngredientsDetail(
    title = "Pizza",
    image = R.drawable.pizza,
    backgroundColor = Orange53
)

val tomato = IngredientsDetail(
    title = "Tomato",
    image = R.drawable.tomato,
    backgroundColor = RedTomato
)

val pasta = IngredientsDetail(
    title = "Pasta",
    image = R.drawable.pasta,
    backgroundColor = YellowPasta
)

val burger = IngredientsDetail(
    title = "Burger",
    image = R.drawable.hamburger,
    backgroundColor = Orange53
)

val egg = IngredientsDetail(
    title = "Egg",
    image = R.drawable.egg,
    backgroundColor = SkinEgg
)

val cheese = IngredientsDetail(
    title = "Cheese",
    image = R.drawable.cheese,
    backgroundColor = YellowCheese
)

val flour = IngredientsDetail(
    title = "Flour",
    image = R.drawable.flour,
    backgroundColor = BrownFlour
)

val milk = IngredientsDetail(
    title = "Milk",
    image = R.drawable.milk,
    backgroundColor = BlueMilk
)
