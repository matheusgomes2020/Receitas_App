package com.matheus.receitasapp.presentation.recipe_detail.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.recipe_detail.DetailsContentTest
import com.matheus.receitasapp.presentation.recipe_detail.IngredientsContainerTest
import com.matheus.receitasapp.presentation.recipe_detail.TopContainerTest


@Preview
@Composable
fun TopContainerTest(){
    Surface(
        shape = RoundedCornerShape(bottomStart = DpDimensions.Dp30, bottomEnd = DpDimensions.Dp30),
        modifier = Modifier
            //.fillMaxSize()
            //.fillMaxWidth()
            .height(450.dp)
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.food),
                    contentScale = ContentScale.FillBounds
                )
            // .height(500.dp)
        ) {
            // Box(contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .padding(DpDimensions.Normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50)),
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.left_chevron_t), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(18.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    //.padding(start = DpDimensions.Normal, top = DpDimensions.Normal)
                    .clip(RoundedCornerShape(DpDimensions.Dp50))
                    // .blur(radius = 16.dp)
                ){
                    Icon(tint = Color.Black, painter = painterResource(id = R.drawable.bookmark), contentDescription = ""
                        , modifier = Modifier
                            .background(Color.White)
                            .padding(DpDimensions.Small)
                            .size(22.dp))
                }
            }
        }
    }

}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrincipalTest() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(if (!isSystemInDarkTheme()) Color.White else Color.Black)
    ) {
        TopContainerTest()
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
        ) {
            DetailsContentTest()
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            IngredientsContainerTest()
        }

    }

}