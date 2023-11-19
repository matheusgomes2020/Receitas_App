package com.matheus.receitasapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matheus.receitasapp.R


val fontFamily2 = FontFamily(
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_extra_bold, FontWeight.ExtraBold),
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_regular, FontWeight.Normal)
)

val fontFamily5 = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

val fontFamily3 = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = SpDimensions.HeadlineLarge,
        lineHeight = 35.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Medium,
        fontSize = SpDimensions.BodyLarge,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = SpDimensions.BodyMedium,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = SpDimensions.BodySmall,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily5,
        fontWeight = FontWeight.Normal,
        fontSize = SpDimensions.TitleMedium,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily5,
        fontWeight = FontWeight.SemiBold,
        fontSize = SpDimensions.TitleLarge,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = SpDimensions.HeadlineMedium,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily2,
        fontWeight = FontWeight.SemiBold,
        fontSize = SpDimensions.TitleSmall,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall =TextStyle(
        fontFamily = fontFamily5,
        fontWeight = FontWeight.SemiBold,
        fontSize = SpDimensions.TitleMedium,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    )

)