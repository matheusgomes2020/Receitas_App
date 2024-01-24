package com.ezzy.quizzo.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.matheus.receitasapp.R

data class OnBoarding(
    @DrawableRes val image: Int,
    @StringRes val title: Int
)


val onBoardingItems = listOf(
    OnBoarding(image = R.drawable.vinegar, title = R.string.favorites),
    OnBoarding(image = R.drawable.boiledegg, title = R.string.home),
    OnBoarding(image = R.drawable.bread, title = R.string.ingredients),
)
