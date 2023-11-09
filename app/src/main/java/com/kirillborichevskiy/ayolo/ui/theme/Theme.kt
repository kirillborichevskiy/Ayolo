package com.kirillborichevskiy.ayolo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kirillborichevskiy.ayolo.ui.theme.color.ElectricBlue
import com.kirillborichevskiy.ayolo.ui.theme.color.GhostWhite
import com.kirillborichevskiy.ayolo.ui.theme.color.PersianBlue
import com.kirillborichevskiy.ayolo.ui.theme.color.White
import com.kirillborichevskiy.ayolo.ui.theme.spacing.LocalSpacing
import com.kirillborichevskiy.ayolo.ui.theme.spacing.Spacing
import com.kirillborichevskiy.ayolo.ui.theme.text.LocalTextSize
import com.kirillborichevskiy.ayolo.ui.theme.text.TextSize
import com.kirillborichevskiy.ayolo.ui.theme.text.Typography

private val MainColorScheme = lightColorScheme(
    primary = White,
    onPrimary = PersianBlue,
    secondary = GhostWhite,
    onSecondary = ElectricBlue,
)

@Composable
internal fun AyoloTheme(
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = MainColorScheme.primary,
            darkIcons = true,
        )
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalTextSize provides TextSize,
    ) {
        MaterialTheme(
            colorScheme = MainColorScheme,
            typography = Typography,
            content = content,
        )
    }
}
