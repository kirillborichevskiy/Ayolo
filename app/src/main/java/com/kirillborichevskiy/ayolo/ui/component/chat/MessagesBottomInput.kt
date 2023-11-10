package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloInputField
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing

@Composable
internal fun MessageBottomInput(
    modifier: Modifier = Modifier,
    inputValue: String,
    onSendClick: () -> Unit,
    onClearValue: () -> Unit,
    onMessageFieldChange: (String) -> Unit,
) {
    val isEnabled = remember(inputValue) { inputValue.isNotBlank() }

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                bottom = MaterialTheme.spacing.extraLarge,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.small,
            ),
    ) {
        AyoloInputField(
            modifier = Modifier.weight(1f),
            value = inputValue,
            placeholderText = stringResource(R.string.type_here),
            onClearClick = onClearValue,
            onValueChange = onMessageFieldChange,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        IconButton(
            modifier = modifier
                .size(MaterialTheme.spacing.extraExtraLarge)
                .clip(shape = CircleShape)
                .alpha(if (isEnabled) 1f else 0.4f),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                )
            },
            onClick = {
                if (isEnabled) {
                    onSendClick()
                }
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MessageBottomInputPreview() {
    AyoloTheme {
    }
}
