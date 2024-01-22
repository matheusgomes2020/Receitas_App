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

data class ItemDetail(
    val title: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color
)

val items = listOf(
    ItemDetail(
        title = "Pizza",
        image = R.drawable.pizza,
        backgroundColor = Orange53
    ),
    ItemDetail(
        title = "Pasta",
        image = R.drawable.pasta,
        backgroundColor = YellowCheese
    ),ItemDetail(
        title = "Burger",
    image = R.drawable.hamburger,
    backgroundColor = OrangeBurguer
    ),
    ItemDetail(
        title = "Sushi",
        image = R.drawable.sushi,
        backgroundColor = RedTomato
    ),
    ItemDetail(
        title = "Taco",
        image = R.drawable.wrap,
        backgroundColor = SkinEgg
    ),
    ItemDetail(
        title = "Hot Dog",
        image = R.drawable.hot_dog,
        backgroundColor = YellowCheese
    ),
    ItemDetail(
        title = "Croissant",
        image = R.drawable.croissant,
        backgroundColor = YellowCheese
    ),
    ItemDetail(
        title = "Tofu",
        image = R.drawable.tofu,
        backgroundColor = BrownFlour
    ),
    ItemDetail(
        title = "Ramen",
        image = R.drawable.ramen,
        backgroundColor = BlueMilk
    ),
    ItemDetail(
        title = "Ravioli",
        image = R.drawable.ravioli,
        backgroundColor = YellowCheese
    ),
    ItemDetail(
        title = "Tortillas",
        image = R.drawable.tortillas,
        backgroundColor = BrownFlour
    ),
    ItemDetail(
        title = "Brigadeiro",
        image = R.drawable.brigadeiro,
        backgroundColor = GreenParsley
    ),ItemDetail(
        title = "Ice cream",
        image = R.drawable.ice_cream,
        backgroundColor = GreenParsley
    )
)

val ingredients = listOf(
    ItemDetail(
        title = "Egg",
        image = R.drawable.egg,
        backgroundColor = SkinEgg
    ),
    ItemDetail(
        title = "Cheese",
        image = R.drawable.cheese,
        backgroundColor = YellowCheese
    ),ItemDetail(
        title = "Flour",
        image = R.drawable.flour,
        backgroundColor = BrownFlour
    ),ItemDetail(
        title = "Milk",
        image = R.drawable.milk,
        backgroundColor = BlueMilk
    ),ItemDetail(
        title = "Vinegar",
        image = R.drawable.vinegar,
        backgroundColor = BrownFlour
    )

)

val pizza = ItemDetail(
    title = "Pizza",
    image = R.drawable.pizza,
    backgroundColor = Orange53
)

val tomato = ItemDetail(
    title = "Tomato",
    image = R.drawable.tomato,
    backgroundColor = RedTomato
)

val pasta = ItemDetail(
    title = "Pasta",
    image = R.drawable.pasta,
    backgroundColor = YellowPasta
)

val burger = ItemDetail(
    title = "Burger",
    image = R.drawable.hamburger,
    backgroundColor = Orange53
)

val egg = ItemDetail(
    title = "Egg",
    image = R.drawable.egg,
    backgroundColor = SkinEgg
)

val cheese = ItemDetail(
    title = "Cheese",
    image = R.drawable.cheese,
    backgroundColor = YellowCheese
)

val flour = ItemDetail(
    title = "Flour",
    image = R.drawable.flour,
    backgroundColor = BrownFlour
)

val milk = ItemDetail(
    title = "Milk",
    image = R.drawable.milk,
    backgroundColor = BlueMilk
)
