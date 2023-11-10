package com.kirillborichevskiy.ayolo.ui.theme.spacing

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal data class Spacing(
    val default: Dp = 0.dp,
    val unitary: Dp = 1.dp,
    val tiny: Dp = 2.dp,
    val extraExtraSmall: Dp = 4.dp,
    val extraSmall: Dp = 8.dp,
    val small: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 36.dp,
    val extraExtraLarge: Dp = 52.dp,
    val giant: Dp = 64.dp,
)

internal val LocalSpacing = staticCompositionLocalOf(::Spacing)

internal val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
