package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.kirillborichevskiy.domain.util.extension.empty
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ChatItem(
    modifier: Modifier = Modifier,
    chatItem: UiChat,
    onLongClick: (chatId: Int) -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = { onLongClick(chatItem.id) },
            )
            .height(IntrinsicSize.Max)
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.extraSmall,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AyoloChatAvatar(letter = chatItem.name.first())
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            AyoloText(
                text = chatItem.name,
                style = MaterialTheme.typography.bodyMedium,
            )
            AyoloText(
                text = chatItem.messages.firstOrNull()?.text ?: String.empty,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        AyoloText(
            text = chatItem.messages.firstOrNull()?.timestamp ?: String.empty,
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
            chatItem = UiChat(
                id = 1,
                name = "John Doe",
                messages = persistentListOf(),
            ),
            onLongClick = {},
            onClick = {},
        )
    }
}
