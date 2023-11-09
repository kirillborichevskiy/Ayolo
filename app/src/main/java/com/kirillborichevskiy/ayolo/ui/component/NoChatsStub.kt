package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing

@Composable
internal fun NoChatsStub(
    modifier: Modifier = Modifier,
    onCreateNewChat: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AyoloText(
            text = stringResource(R.string.create_first_chat_now),
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        AyoloPivotButton(
            text = stringResource(R.string.create_new_chat),
            enabled = true,
            onClick = onCreateNewChat,
        )
    }
}
