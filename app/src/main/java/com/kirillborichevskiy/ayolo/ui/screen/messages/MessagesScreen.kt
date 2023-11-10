package com.kirillborichevskiy.ayolo.ui.screen.messages

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.navigation.NavigationRoute
import com.kirillborichevskiy.ayolo.ui.component.chat.AyoloChatAvatar
import com.kirillborichevskiy.ayolo.ui.component.chat.MessageBottomInput
import com.kirillborichevskiy.ayolo.ui.component.chat.MessageItem
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloTopBar
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
@Destination(route = NavigationRoute.MESSAGES_SCREEN)
internal fun MessagesScreen(
    navigator: DestinationsNavigator,
    chatId: Int,
    chatName: String,
) {
    val viewModel = hiltViewModel<MessagesViewModel>()
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val messageText by viewModel.messageText.collectAsStateWithLifecycle()

    val context = LocalContext.current

    MessagesScreenContent(
        messages = messages,
        chatName = chatName,
        messageText = messageText,
        onClearMessageText = viewModel::onClearMessageText,
        onSendClick = { viewModel.onSendNewMessage(chatId) },
        onBackPressed = navigator::popBackStack,
        onMessageFieldChange = viewModel::onMessageTextChange,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.onPreloadMessages(chatId)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.shouldShowToastError.onEach {
            Toast.makeText(
                context,
                context.getString(R.string.something_went_wrong),
                Toast.LENGTH_SHORT,
            ).show()
        }.launchIn(this)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MessagesScreenContent(
    messages: ImmutableList<UiMessage>,
    chatName: String,
    messageText: String,
    onClearMessageText: () -> Unit,
    onSendClick: () -> Unit,
    onBackPressed: () -> Unit,
    onMessageFieldChange: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            AyoloTopBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = MaterialTheme.spacing.medium),
                title = chatName,
                startComposable = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            tint = MaterialTheme.colorScheme.onPrimary,
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                        )
                    }
                },
                endComposable = {
                    Row {
                        AyoloChatAvatar(letter = chatName.first())
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
                    }
                },
            )
        },
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .padding(bottom = MaterialTheme.spacing.extraSmall),
                state = lazyListState,
                reverseLayout = true,
                horizontalAlignment = Alignment.End,
            ) {
                itemsIndexed(
                    items = messages,
                    key = { _, message -> message.id },
                ) { _, message ->
                    MessageItem(
                        modifier = Modifier.animateItemPlacement(),
                        text = message.text,
                        timestamp = message.timestamp,
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraSmall))
                }
            }
            MessageBottomInput(
                inputValue = messageText,
                onSendClick = onSendClick,
                onClearValue = onClearMessageText,
                onMessageFieldChange = onMessageFieldChange,
            )
        }
    }
}
