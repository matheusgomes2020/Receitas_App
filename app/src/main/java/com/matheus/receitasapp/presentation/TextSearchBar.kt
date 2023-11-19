package com.matheus.receitasapp.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextSearchBar(title: String){
    Text(text = title,
        modifier = Modifier,
        color = Color.LightGray,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
    )
}