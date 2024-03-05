package com.matheus.receitasapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.DpDimensions

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    //onNotificationClick: () -> Unit = {},
    onLogoClick: () -> Unit = {},
    isProfileScreen: Boolean = false,
    @DrawableRes icon1: Int,
   // @DrawableRes icon2: Int,
    title: String,
    imageUrl: String
) {

    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Normal,
                vertical = DpDimensions.Small
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.yumlogoo),
                contentDescription = "Logo",
                modifier = Modifier.size(DpDimensions.Dp30)
                    .clickable { onLogoClick() }
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                //color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = DpDimensions.Normal),
                textAlign = TextAlign.Start,
                maxLines = 1
            )

            if (isProfileScreen) {
                AsyncImage(
                    model =
                   // if (imageUrl != "sem imagem") imageUrl else
                        R.drawable.bread,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .clickable {
                                   onSearchClick()
                        },
                    contentScale = ContentScale.Crop,
                )
            } else {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        painter = painterResource(id = icon1),
                        contentDescription = null,
                    )
                }
            }

        }
    }
}