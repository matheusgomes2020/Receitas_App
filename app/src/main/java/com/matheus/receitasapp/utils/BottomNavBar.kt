package com.matheus.receitasapp.utils

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.navigation.utils.bottomNavScreens
import com.matheus.receitasapp.ui.theme.DarkGrey11
import com.matheus.receitasapp.ui.theme.GreenApp
import com.matheus.receitasapp.ui.theme.Grey46
import com.matheus.receitasapp.ui.theme.ReceitasAppTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    visible: Boolean = true
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Column(
            modifier = Modifier
                .padding(DpDimensions.Small)
        ) {
            NavigationBar(
                containerColor = if (isSystemInDarkTheme()) DarkGrey11 else White,

                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        clip = true
                        shape = RoundedCornerShape(DpDimensions.Dp30)
                        shadowElevation = 3f
                    }
                    .height(75.dp),
                tonalElevation = 3.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavScreens.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                tint = if (selected) GreenApp else Grey46
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.label),
                                style = MaterialTheme.typography.bodySmall,
                                color =  if (selected) GreenApp else Grey46
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = if (isSystemInDarkTheme()) DarkGrey11 else White,

                        )
                    )
                }
            }
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomNavBarPreview() {
    ReceitasAppTheme {
        BottomNavBar(navController = rememberNavController())
    }
}
