package com.matheus.receitasapp.presentation.filters

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.searchRecipes.RowTypeT
import com.matheus.receitasapp.ui.theme.GreenApp

@Composable
fun ChipGroupTypeOfMeal(
    ii: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(ii) }

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
fun ChipGroupCuisineType(
    ii: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(ii) }

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
    ii: String = "",
    onSelected: (String) -> Unit,
    list: List<String>) {
    var selected by remember { mutableStateOf(ii) }

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
fun ChipGroupComposeT(list: List<String>) {


    var selected by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            //.padding(start = 30.dp, top = 50.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        list.forEach { it ->
            RowTypeT(
                title = it,
                selectedd = true,
                onSelected = {it ->
                    Log.d("AMAZFIT", "ChipGroupCompose: $it")
                }
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {

    val isSelected = selected == title

    val background = if (isSelected) GreenApp else Color.White
    val contentColor = if (isSelected) Color.White else Color.Black

    Surface(
        onClick = {
            onSelected(title)
        },
        modifier = Modifier
            .padding(end = DpDimensions.Small),

        shape = RoundedCornerShape(DpDimensions.Smallest),
        color = background,
        shadowElevation = 3.dp
    ) {
        Box(
            modifier = Modifier
                .padding( horizontal = DpDimensions.Small, vertical = DpDimensions.Smallest )
        ) {
            Text(text = title, color = contentColor)
        }
    }

}

@Composable
fun ChipGroupCompose2() {

    val chipList: List<String> = listOf(
        "Spent",
        "Add Funds",
        "Savings"
    )

    var selected by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(start = 30.dp,top = 50.dp)
            .fillMaxWidth()
    ) {
        chipList.forEach { it ->
            Chip2(
                title = it,
                selected = selected,
                onSelected = {
                    selected = it
                }
            )
        }
    }

}

@Composable
fun Chip2(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {

    val isSelected = selected == title

    val background = if (isSelected) Color.Blue else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(35.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    onSelected(title)
                }
            )
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AnimatedVisibility(visible = isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "check",
                    tint = Color.White
                )
            }

            Text(text = title, color = contentColor, fontSize = 16.sp)

        }
    }

}