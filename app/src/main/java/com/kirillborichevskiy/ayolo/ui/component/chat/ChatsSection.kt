package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.component.AyoloFloatingButton
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloTopBar
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ChatsSection(
    modifier: Modifier = Modifier,
    chats: ImmutableList<UiChat>,
    isSelected: Boolean,
    onChatClick: (Int, String) -> Unit,
    onLongClick: (Int) -> Unit,
    onCreateNewChat: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        floatingActionButton = {
            AyoloFloatingButton(onClick = onCreateNewChat)
        },
        topBar = {
            AyoloTopBar(
                title = stringResource(R.string.recent_chats),
                endComposable = {
                    IconButton(onClick = if (isSelected) onDeleteClick else onCreateNewChat) {
                        Icon(
                            tint = MaterialTheme.colorScheme.onPrimary,
                            painter = painterResource(
                                id = if (isSelected) {
                                    R.drawable.ic_trash
                                } else {
                                    R.drawable.ic_plus
                                },
                            ),
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.primary),
            state = lazyListState,
        ) {
            itemsIndexed(
                items = chats,
                key = { _, chat -> chat.id },
            ) { _, chat ->
                ChatItem(
                    modifier = Modifier
                        .background(
                            if (chat.isSelected) {
                                MaterialTheme.colorScheme.onTertiary
                            } else {
                                MaterialTheme.colorScheme.secondary
                            },
                        )
                        .fillMaxWidth()
                        .animateItemPlacement(),
                    chatItem = chat,
                    onLongClick = onLongClick,
                    onClick = { onChatClick(chat.id, chat.name) },
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))
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
                    messages = persistentListOf(
                        UiMessage(
                            id = 1,
                            text = "Hello guys, we have discussed about ...",
                            timestamp = "10:00 AM",
                        ),
                    ),
                ),
            ),
            onCreateNewChat = {},
            onLongClick = {},
            onDeleteClick = {},
            isSelected = false,
            onChatClick = { _, _ -> },
        )
    }
}
