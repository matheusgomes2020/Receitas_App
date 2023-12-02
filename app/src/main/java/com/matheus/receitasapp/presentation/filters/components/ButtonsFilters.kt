package com.matheus.receitasapp.presentation.filters.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.ui.theme.GreenApp
@Composable
fun ButtonsFilters(
    onClickButtonReset: () -> Unit,
    onClickButtonApply: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DpDimensions.Normal)
    ) {
        OutlinedButton(
            onClick = {
                      onClickButtonReset()
            },
            modifier = Modifier
                .weight(1f)
                .height(41.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(DpDimensions.Small),
            border = BorderStroke(1.dp, GreenApp)
        )
        {
            Text(
                text = "Reset",
                color = GreenApp,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Button(
            onClick = {
                      onClickButtonApply()
            },
            modifier = Modifier
                .weight(1f)
                .height(41.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenApp
            ),
            shape = RoundedCornerShape(DpDimensions.Small),
        ) {
            Text(
                text = "Apply",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}