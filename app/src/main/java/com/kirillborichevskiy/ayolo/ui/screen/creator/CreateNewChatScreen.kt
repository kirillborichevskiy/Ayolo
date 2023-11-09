package com.kirillborichevskiy.ayolo.ui.screen.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.navigation.NavigationRoute
import com.kirillborichevskiy.ayolo.ui.component.AyoloTopBar
import com.kirillborichevskiy.ayolo.ui.component.CreateChatSection
import com.kirillborichevskiy.ayolo.ui.screen.chats.CreateChatViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(route = NavigationRoute.CREATE_NEW_CHAT_SCREEN)
internal fun CreateNewChatScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<CreateChatViewModel>()
    val chatName by viewModel.chatName.collectAsStateWithLifecycle()

    CreateNewChatScreenContent(
        chatName = chatName,
        onChatNameChange = viewModel::onChatNameChange,
        onClearChatName = viewModel::onClearChatName,
        onCreateNewChat = { chatName ->
            viewModel.onCreateNewChat(chatName)
            navigator.popBackStack()
        },
        onBackPressed = navigator::popBackStack,
    )
}

@Composable
private fun CreateNewChatScreenContent(
    chatName: String,
    onChatNameChange: (String) -> Unit,
    onClearChatName: () -> Unit,
    onCreateNewChat: (String) -> Unit,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AyoloTopBar(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            startComposable = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onPrimary,
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                    )
                }
            },
        )
        CreateChatSection(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.create_first_chat_now),
            subtitle = stringResource(R.string.specify_chat_name),
            buttonText = stringResource(R.string.create_new_chat),
            chatName = chatName,
            onClearChatName = onClearChatName,
            onChatNameChange = onChatNameChange,
            onCreateNewChat = onCreateNewChat,
        )
    }
}
