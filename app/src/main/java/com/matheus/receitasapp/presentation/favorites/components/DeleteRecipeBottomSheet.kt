package com.matheus.receitasapp.presentation.favorites.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.ui.theme.Grey62
import com.matheus.receitasapp.ui.theme.Red70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteRecipeBottomSheet(
    name: String,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
    onLogout: (Boolean) -> Unit,
    onCancel: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(
            DpDimensions.Dp20
        ),
        sheetState = bottomSheetState,
        modifier = modifier
            .padding(
                DpDimensions.Dp20)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(DpDimensions.Normal)
                .fillMaxWidth(),
        ) {

            Text(text = "Remover favorito",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red)

            Spacer(modifier = Modifier.height(DpDimensions.Dp40))
            val aText = buildAnnotatedString {
                append("Remover ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(name)
                }
                append(" dos favoritos?")
            }
            Text(text = aText,
                style = MaterialTheme.typography.bodyMedium,
                //color =MaterialTheme.colorScheme.inversePrimary,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(DpDimensions.Dp20))

            TwoButtonsColumn(
                leftButtonText = "cancelar",
                rightButtonText = "sim",
                onLeftButtonClick = { onCancel() },
                onRightButtonClick = { onLogout(true) })

        }
    }

}

@Composable
fun TwoButtonsColumn(
    leftButtonText: String,
    rightButtonText: String,
    onLeftButtonClick:() -> Unit,
    onRightButtonClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        Column(

            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 20.dp
                )
        ) {
            Button(
                onClick = {
                    onRightButtonClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red70
                ),
                shape = RoundedCornerShape(DpDimensions.Dp20)
            ) {
                Text(
                    text = rightButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            OutlinedButton(
                onClick = { onLeftButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey62
                ),
                shape = RoundedCornerShape(DpDimensions.Dp20),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(
                    text = leftButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}