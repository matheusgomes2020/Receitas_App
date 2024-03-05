package com.ezzy.quizzo.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.matheus.receitasapp.R

data class OnBoarding(
    @DrawableRes val image: Int,
    @StringRes val title: Int
)


val onBoardingItems = listOf(
    OnBoarding(image = R.drawable.recipes, title = R.string.favorites_onboarding),
    OnBoarding(image = R.drawable.research, title = R.string.search_onboarding),
    OnBoarding(image = R.drawable.recipe, title = R.string.ingredients_onboarding),
)
