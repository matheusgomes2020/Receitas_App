package com.matheus.receitasapp.presentation.settings

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.receitasapp.R
import com.matheus.receitasapp.common.AppBarWithBack
import com.matheus.receitasapp.common.CustomPadding
import com.matheus.receitasapp.common.DpDimensions
import com.matheus.receitasapp.presentation.settings.components.AccountItem
import com.matheus.receitasapp.ui.theme.DarkGrey11

@Composable
fun SettingsScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val webIntentPrivacy: Intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://doc-hosting.flycricket.io/pop-cine-privacy-policy/7016f44d-67a3-40d5-8700-868e80fab0d1/privacy")
    )

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }
    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Settings", backIcon = Icons.Default.ArrowBack, onBackClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
            Spacer(modifier = androidx.compose.ui.Modifier.height(DpDimensions.Small))

            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
//                AccountItem(
//                    icon = R.drawable.account, title = "Dados",
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Toast.makeText( context, "Em breve!!!", Toast.LENGTH_SHORT ).show()
//                }
//                LanguageItem(
//                    icon = R.drawable.language,
//                    title = "Idioma",
//                    language = "Português (BR)",
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Toast.makeText( context, "Em breve!!!", Toast.LENGTH_SHORT ).show()
//                }
//
//                DarkModeItem(
//                    icon = R.drawable.dark_mode,
//                    title = "Modo escuro",
//                    //onState = {  },
//                    //onCheckChange = viewModel::onCheckChange
//                )

                AccountItem(
                    icon = R.drawable.privacy,
                    title = "Política de privacidade",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    context.startActivity(webIntentPrivacy)
                }

                AccountItem(
                    icon = R.drawable.info,
                    title = "Sobre",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Toast.makeText(context, "Em breve!!!", Toast.LENGTH_SHORT).show()
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }
}