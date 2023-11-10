package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.component.AyoloPivotButton
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloInputField
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.kirillborichevskiy.domain.util.extension.empty

@Composable
internal fun CreateChatSection(
    modifier: Modifier = Modifier,
    title: String = String.empty,
    subtitle: String = String.empty,
    buttonText: String = String.empty,
    chatName: String,
    onChatNameChange: (String) -> Unit,
    onClearChatName: () -> Unit,
    onCreateNewChat: (String) -> Unit,
) {
    val isEnabled = remember(chatName) { chatName.isNotBlank() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AyoloText(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
        AyoloText(
            text = subtitle,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.giant))
        AyoloInputField(
            modifier = Modifier.fillMaxWidth(),
            value = chatName,
            placeholderText = stringResource(id = R.string.chat_name),
            onClearClick = onClearChatName,
            onValueChange = onChatNameChange,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        AyoloPivotButton(
            text = buttonText,
            enabled = isEnabled,
            onClick = {
                if (isEnabled) {
                    onCreateNewChat(chatName)
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CreateChatSectionPreview() {
    AyoloTheme {
        CreateChatSection(
            title = stringResource(R.string.create_first_chat_now),
            subtitle = stringResource(R.string.specify_chat_name),
            buttonText = stringResource(R.string.create_new_chat),
            chatName = String.empty,
            onChatNameChange = {},
            onClearChatName = {},
            onCreateNewChat = {},
        )
    }
}
