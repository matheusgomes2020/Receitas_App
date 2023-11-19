package com.matheus.receitasapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

fun slideOutVerticallyEnterAnimation() =
    slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

fun slideInVerticallyEnterAnimation() =
    slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )