package com.kirillborichevskiy.ayolo.ui.theme.text

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontWeight
import com.kirillborichevskiy.ayolo.R

internal val Mulish = FontFamily(
    Font(
        resId = R.font.mulish_light,
        weight = FontWeight.Light,
        loadingStrategy = FontLoadingStrategy.Async,
    ),

    Font(
        resId = R.font.mulish_regular,
        weight = FontWeight.Medium,
        loadingStrategy = FontLoadingStrategy.Async,
    ),

    Font(
        resId = R.font.mulish_bold,
        weight = FontWeight.Bold,
        loadingStrategy = FontLoadingStrategy.Async,
    ),
)
