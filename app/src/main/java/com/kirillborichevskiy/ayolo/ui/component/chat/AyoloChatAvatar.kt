package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing

@Composable
internal fun AyoloChatAvatar(
    modifier: Modifier = Modifier,
    letter: Char,
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
            )
            .size(MaterialTheme.spacing.extraExtraLarge),
        contentAlignment = Alignment.Center,
    ) {
        AyoloText(
            text = letter.uppercase(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AyoloChatAvatarPreview() {
    AyoloTheme {
        AyoloChatAvatar(letter = 'A')
    }
}
