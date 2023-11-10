package com.kirillborichevskiy.ayolo.ui.theme.text

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.kirillborichevskiy.ayolo.ui.theme.color.ElectricBlue
import com.kirillborichevskiy.ayolo.ui.theme.color.PersianBlue
import com.kirillborichevskiy.ayolo.ui.theme.color.White

internal val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = TextSize.extraLarge,
        fontWeight = FontWeight.Bold,
        fontFamily = Mulish,
        color = PersianBlue,
        textAlign = TextAlign.Center,
    ),

    bodyMedium = TextStyle(
        fontSize = TextSize.small,
        fontWeight = FontWeight.Bold,
        fontFamily = Mulish,
        color = PersianBlue,
        textAlign = TextAlign.Center,
    ),

    headlineSmall = TextStyle(
        fontSize = TextSize.small,
        fontWeight = FontWeight.Medium,
        fontFamily = Mulish,
        color = ElectricBlue,
        textAlign = TextAlign.Center,
    ),

    titleLarge = TextStyle(
        fontSize = TextSize.large,
        fontWeight = FontWeight.Bold,
        fontFamily = Mulish,
        color = PersianBlue,
        textAlign = TextAlign.Center,
    ),

    titleMedium = TextStyle(
        fontSize = TextSize.medium,
        fontWeight = FontWeight.Bold,
        fontFamily = Mulish,
        color = White,
        textAlign = TextAlign.Center,
    ),

    bodyLarge = TextStyle(
        fontSize = TextSize.extraSmall,
        fontWeight = FontWeight.Light,
        fontFamily = Mulish,
        color = ElectricBlue,
        textAlign = TextAlign.Center,
    ),
)
