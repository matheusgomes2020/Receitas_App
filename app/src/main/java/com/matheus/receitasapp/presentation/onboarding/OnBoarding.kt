package com.ezzy.quizzo.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.matheus.receitasapp.R

data class OnBoarding(
    @DrawableRes val image: Int,
    @StringRes val title: Int
)


val onBoardingItems = listOf(
    OnBoarding(image = R.drawable.vinegar, title = R.string.favorites_onboarding),
    OnBoarding(image = R.drawable.search, title = R.string.search_onboarding),
    OnBoarding(image = R.drawable.bread, title = R.string.ingredients_onboarding),
)
