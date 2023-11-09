package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ChatsSection(
    modifier: Modifier = Modifier,
    chats: ImmutableList<UiChat>,
    onChatClick: () -> Unit,
    onCreateNewChat: () -> Unit,
) {
    val lazyListState = rememberLazyListState()

    AyoloFloatingButton(onClick = onCreateNewChat)
    Column(
        modifier = modifier,
    ) {
        AyoloTopBar(
            title = stringResource(R.string.recent_chats),
            endComposable = {
                IconButton(onClick = onCreateNewChat) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onPrimary,
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                    )
                }
            },
        )
        LazyColumn(
            modifier = modifier
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.primary),
            state = lazyListState,
        ) {
            itemsIndexed(
                items = chats,
                key = { _, chat -> chat.id },
            ) { _, chat ->
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    name = chat.name,
                    message = chat.messages.firstOrNull()?.text.orEmpty(),
                    timestamp = chat.messages.firstOrNull()?.timestamp.orEmpty(),
                    onClick = onChatClick,
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ChatsSectionPreview() {
    AyoloTheme {
        ChatsSection(
            modifier = Modifier.fillMaxSize(),
            chats = persistentListOf(
                UiChat(
                    id = 1,
                    name = "Fullsnack Developers",
                    messages = listOf(
                        UiMessage(
                            id = 1,
                            text = "Something here",
                            timestamp = "10:00 AM",
                        ),
                    ),
                ),
                UiChat(
                    id = 2,
                    name = "New Chat 2",
                    messages = listOf(
                        UiMessage(
                            id = 1,
                            text = "First message in Chat 2",
                            timestamp = "08:15 PM",
                        ),
                    ),
                ),
                UiChat(
                    id = 3,
                    name = "New Chat 3",
                    messages = listOf(
                        UiMessage(
                            id = 1,
                            text = "First message in Chat 3",
                            timestamp = "5:32 PM",
                        ),
                    ),
                ),
            ),
            onCreateNewChat = {},
            onChatClick = {},
        )
    }
}
