package com.kirillborichevskiy.ayolo.ui.screen.chats

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.navigation.NavigationRoute
import com.kirillborichevskiy.ayolo.ui.component.chat.ChatsSection
import com.kirillborichevskiy.ayolo.ui.component.compound.NoChatsStub
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.screen.destinations.CreateNewChatScreenDestination
import com.kirillborichevskiy.ayolo.ui.screen.destinations.MessagesScreenDestination
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
@Destination(route = NavigationRoute.CHAT_LIST_SCREEN)
internal fun ChatsScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<ChatsViewModel>(LocalContext.current as ComponentActivity)
    val chats by viewModel.chats.collectAsStateWithLifecycle()
    val isSelected by viewModel.isSelected.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.shouldShowToastError.onEach {
            Toast.makeText(
                context,
                context.getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT,
            ).show()
        }.launchIn(this)
    }

    ChatListScreenContent(
        chats = chats,
        isSelected = isSelected,
        onCreateNewChat = { navigator.navigate(CreateNewChatScreenDestination) },
        onChatClick = { chatId, chatName ->
            navigator.navigate(MessagesScreenDestination(chatId = chatId, chatName = chatName))
        },
        onLongClick = viewModel::onLongChatsClick,
        onDeleteClick = viewModel::onDeleteClick,
    )
}

@Composable
private fun ChatListScreenContent(
    chats: ImmutableList<UiChat>,
    isSelected: Boolean,
    onCreateNewChat: () -> Unit,
    onChatClick: (Int, String) -> Unit,
    onLongClick: (chatId: Int) -> Unit,
    onDeleteClick: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    systemUiController.apply {
        setStatusBarColor(
            color = MaterialTheme.colorScheme.primary,
            darkIcons = true,
        )
        setNavigationBarColor(
            color = MaterialTheme.colorScheme.primary,
            darkIcons = true,
        )
    }

    if (chats.isNotEmpty()) {
        ChatsSection(
            chats = chats,
            isSelected = isSelected,
            onChatClick = onChatClick,
            onCreateNewChat = onCreateNewChat,
            onLongClick = onLongClick,
            onDeleteClick = onDeleteClick,
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
