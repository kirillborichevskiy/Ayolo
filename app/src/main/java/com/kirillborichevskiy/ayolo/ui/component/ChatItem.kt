package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.kirillborichevskiy.domain.util.extension.empty

@Composable
internal fun ChatItem(
    modifier: Modifier = Modifier,
    name: String = String.empty,
    message: String = String.empty,
    timestamp: String = String.empty,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(onClick = onClick)
            .height(IntrinsicSize.Max)
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.extraSmall,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AyoloChatAvatar(letter = name.first())
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            AyoloText(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
            )
            AyoloText(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        AyoloText(
            text = timestamp,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ChatItemPreview() {
    AyoloTheme {
        ChatItem(
            name = "John Doe",
            message = "Some message is here from John",
            timestamp = "12:00AM",
            onClick = {},
        )
    }
}
