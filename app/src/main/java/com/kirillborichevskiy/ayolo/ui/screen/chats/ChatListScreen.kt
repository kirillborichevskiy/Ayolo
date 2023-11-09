package com.kirillborichevskiy.ayolo.ui.screen.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirillborichevskiy.ayolo.navigation.NavigationRoute
import com.kirillborichevskiy.ayolo.ui.component.ChatsSection
import com.kirillborichevskiy.ayolo.ui.component.NoChatsStub
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.ImmutableList

@Composable
@RootNavGraph(start = true)
@Destination(route = NavigationRoute.CHAT_LIST_SCREEN)
internal fun ChatListScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<ChatsViewModel>()
    val chats by viewModel.chats.collectAsStateWithLifecycle()

    ChatListScreenContent(
        onCreateNewChat = { navigator.navigate(NavigationRoute.CREATE_NEW_CHAT_SCREEN) },
        chats = chats,
    )
}

@Composable
private fun ChatListScreenContent(
    onCreateNewChat: () -> Unit,
    chats: ImmutableList<UiChat>,
) {
    if (chats.isNotEmpty()) {
        ChatsSection(
            chats = chats,
            onChatClick = {},
            onCreateNewChat = onCreateNewChat,
        )
    } else {
        NoChatsStub(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = MaterialTheme.spacing.medium),
            onCreateNewChat = onCreateNewChat,
        )
    }
}
