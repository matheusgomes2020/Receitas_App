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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.Grey62

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteRecipeBottomSheet(
    name: String,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
    onDelete: (Boolean) -> Unit,
    onCancel: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(
            DpDimensions.Normal
        ),
        sheetState = bottomSheetState,
        modifier = modifier
//            .padding(
//                DpDimensions.Dp20)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(DpDimensions.Normal)
                .fillMaxWidth(),
        ) {

            Text(text = "Remove favorite",
                style = MaterialTheme.typography.titleLarge,
                color = GreenApp)

            Spacer(modifier = Modifier.height(DpDimensions.Dp25))
            val aText = buildAnnotatedString {
                append("Dou you want remove ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(name)
                }
                append("?")
            }
            Text(text = aText,
                style = MaterialTheme.typography.bodyMedium,
                //color =MaterialTheme.colorScheme.inversePrimary,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(DpDimensions.Dp20))

            TwoButtonsColumn(
                leftButtonText = "cancel",
                rightButtonText = "yes",
                onLeftButtonClick = { onCancel() },
                onRightButtonClick = { onDelete(true) })
            Spacer(modifier = Modifier.height(DpDimensions.Dp40))

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
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenApp
                ),
                shape = RoundedCornerShape(DpDimensions.Small)
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
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey62
                ),
                shape = RoundedCornerShape(DpDimensions.Small),
                border = BorderStroke(1.dp, GreenApp)
            ) {
                Text(
                    text = leftButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}