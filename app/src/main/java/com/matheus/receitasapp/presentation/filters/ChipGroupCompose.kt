package com.matheus.receitasapp.presentation.filters

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.GreenAppDark

@Composable
fun ChipGroupTypeOfMeal(
    ii: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(ii) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        list.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    onSelected(it)
                }
            )
        }
    }
}

@Composable
fun ChipGroupCuisineType(
    filter: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(filter) }

    Row(
        modifier = Modifier
            //.padding(start = 30.dp, top = 50.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        list.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    Log.d("AMAZFIT", "ChipGroupCompose: $it")
                    onSelected(it)
                }
            )
        }
    }
}

@Composable
fun ChipGroupDiet(
    filter: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(filter) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        list.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    onSelected(it)
                }
            )
        }
    }
}

@Composable
fun ChipGroup(
    filter: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(filter) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        list.forEach { it ->
            Chip(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                    onSelected(it)
                }
            )
        }
    }
}


@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {
    val isSelected = selected == title
    val background = if (isSelected) GreenApp else Color.White
    val backgroundDark = if (isSelected) GreenAppDark else DarkGrey11
    val contentColor = if (isSelected) Color.White else Color.Black
    val contentColorDark = if (isSelected) Color.White else Color.White

    Surface(
        onClick = {
            onSelected(title)
        },
        modifier = Modifier
            .padding(end = DpDimensions.Small),

        shape = RoundedCornerShape(DpDimensions.Smallest),
        color = if (isSystemInDarkTheme())backgroundDark else background,
        shadowElevation = 3.dp
    ) {
        Box(
            modifier = Modifier
                .padding( horizontal = DpDimensions.Small, vertical = DpDimensions.Smallest )
        ) {
            Text(text = title, color = if (isSystemInDarkTheme()) contentColorDark else contentColor)
        }
    }
}

