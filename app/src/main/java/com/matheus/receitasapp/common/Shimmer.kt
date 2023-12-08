package com.matheus.receitasapp.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.receitasapp.R
import com.matheus.receitasapp.data.remote.dto.Recipe
import com.matheus.receitasapp.navigation.NavDestinations
import com.matheus.receitasapp.presentation.recipe_detail.DetailsContentTest
import com.matheus.receitasapp.presentation.recipe_detail.IngredientItemTest2
import com.matheus.receitasapp.presentation.recipe_detail.IngredientsContainerTest
import com.matheus.receitasapp.presentation.recipe_detail.TopContainerTest
import com.matheus.receitasapp.presentation.recipe_detail.components.IngredientsDetail
import com.matheus.receitasapp.presentation.recipe_detail.components.ingredients
import com.matheus.receitasapp.ui.theme.AppColor
import com.matheus.receitasapp.ui.theme.BlueGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.RoyalBlue65
import com.matheus.receitasapp.ui.theme.fontFamily2
import com.matheus.receitasapp.ui.theme.fontFamily3
import kotlin.math.roundToInt

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShimmerRecipeDetail(
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopContainerShimmer()
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(start = DpDimensions.Dp25, top = 380.dp, end = DpDimensions.Dp25)
        ) {
            DetailsContentShimmer()
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            IngredientsContainerShimmer()
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
fun RecipeShimmerGridItem () {

        Box(
            modifier = Modifier
            .width(100.dp)
            .height(250.dp)
                .clip(RoundedCornerShape(DpDimensions.Small))
                .shimmerEffect(),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(DpDimensions.Small)
            ) {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(20.dp)
                        .shimmerEffect(),
                )
            }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
fun HomeCardShimmer() {
    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            color = RoyalBlue65,
            modifier = Modifier
                .width(200.dp)
                .height(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(170.dp)
                    .height(250.dp)
                    .shimmerEffect()
            )

        }
        Column(
            modifier = Modifier
                .padding(horizontal = 1.dp, vertical = DpDimensions.Small)
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(16.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height( DpDimensions.Smallest ))
            Row {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(15.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width( DpDimensions.Smallest ))
                Box(
                    modifier = Modifier
                        .width(65.dp)
                        .height(15.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview
@Composable
fun IngredientsContainerShimmer() {
    Column(modifier = Modifier
        .padding(vertical = DpDimensions.Small),
        verticalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier
            .width(90.dp)
            .height(23.dp)
            .shimmerEffect()
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = DpDimensions.Dp20)
        ){
            items(5) {
                IngredientItemShimmer()
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
fun RecipeSearchItemShimmer() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(DpDimensions.Smallest),
        // shadowElevation = 3.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(DpDimensions.Smallest)
                .background(Color.White),
            //  contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(DpDimensions.Small))
                        .shimmerEffect(),
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Smallest),
                    modifier = Modifier
                        .padding(DpDimensions.Smallest) ) {
                    Box(
                        modifier = Modifier
                            .width(245.dp)
                            .height(20.dp)
                            .shimmerEffect())
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(13.dp)
                                    .shimmerEffect()
                            )
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(13.dp)
                                    .shimmerEffect()
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(13.dp)
                                    .shimmerEffect()
                            )
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(13.dp)
                                    .shimmerEffect()
                            )
                        }
                    }
                }
//                Box(
//                    modifier = Modifier
//                        .size(13.dp)
//                        .shimmerEffect()
//                )
            }

        }
    }
}

@Composable
fun IngredientItemShimmer() {

    Column {
        Surface(
            shape = RoundedCornerShape(DpDimensions.Small),
            shadowElevation = 3.dp,
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(modifier = Modifier
                .size(80.dp)
                .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column {
            Box(modifier = Modifier
                .width(80.dp)
                .height(10.dp)
                .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(3.dp))
            Box(modifier = Modifier
                .width(80.dp)
                .height(9.dp)
                .shimmerEffect()
            )
        }
    }
}

@Preview
@Composable
fun TopContainerShimmer(){
    Surface(
        shape = RoundedCornerShape(bottomStart = DpDimensions.Dp30, bottomEnd = DpDimensions.Dp30),
        modifier = Modifier
            //.fillMaxSize()
            //.fillMaxWidth()
            .height(450.dp)
    ) {
        Box(
            modifier = Modifier
                .shimmerEffect()
            // .height(500.dp)
        ) {
            // Box(contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .padding(DpDimensions.Normal),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(DpDimensions.Dp50))
                    .size(40.dp)
                    .background(Color.White),
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(DpDimensions.Dp50))
                    .size(40.dp)
                    .background(Color.White),
                )
            }
        }
    }

}

@Preview
@Composable
fun DetailsContentShimmer(){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier,
        //.fillMaxWidth()
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier
            //.clip(RoundedCornerShape(DpDimensions.Dp30))
            .padding(vertical = DpDimensions.Normal),
            contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = DpDimensions.Normal)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(9.dp)
                            .shimmerEffect()
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DpDimensions.Normal)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .width(45.dp)
                                .height(20.dp)
                                .shimmerEffect()
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .width(45.dp)
                                .height(20.dp)
                                .shimmerEffect()
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .shimmerEffect()
                        )
                        Box(
                            modifier = Modifier
                                .width(45.dp)
                                .height(20.dp)
                                .shimmerEffect()
                        )
                    }
                }

            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf( IntSize.Zero )
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween( 1000 )
        )
    )
    background(
        brush = Brush.linearGradient(

            colors = if (isSystemInDarkTheme()) {
                listOf(
                    Color(0xFF165A31),
                    Color(0xFF2FC069),
                    Color(0xFF158140)
                )

            } else {
                listOf(
                    Color(0xFF4DB978),
                    Color(0xFF2FC069),
                    Color(0xFF158140)
                )
            },
            start = Offset( startOffsetX, 0f ),
            end = Offset( startOffsetX + size.width.toFloat(), size.height.toFloat() )
        )
    )

        .onGloballyPositioned {
            size =  it.size
        }
}