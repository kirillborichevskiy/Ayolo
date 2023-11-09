package com.kirillborichevskiy.ayolo.ui.theme.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
internal object TextSize {
    val extraSmall: TextUnit = 12.sp
    val small: TextUnit = 14.sp
    val medium: TextUnit = 16.sp
    val large: TextUnit = 18.sp
    val extraLarge: TextUnit = 20.sp
    val giant: TextUnit = 24.sp
}

internal val LocalTextSize = staticCompositionLocalOf { TextSize }

internal val MaterialTheme.textSize: TextSize
    @Composable
    @ReadOnlyComposable
    get() = LocalTextSize.current
