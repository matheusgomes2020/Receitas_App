package com.matheus.receitasapp.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchIcon(){
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon",
        modifier = Modifier.size(20.dp),
        tint = Color.LightGray
    )
}