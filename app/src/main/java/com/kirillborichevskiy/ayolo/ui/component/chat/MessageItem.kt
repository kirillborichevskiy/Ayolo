package com.kirillborichevskiy.ayolo.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing

@Composable
internal fun MessageItem(
    modifier: Modifier = Modifier,
    text: String,
    timestamp: String,
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(
                    topStart = MaterialTheme.spacing.extraSmall,
                    topEnd = MaterialTheme.spacing.extraSmall,
                    bottomStart = MaterialTheme.spacing.extraSmall,
                ),
            )
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.extraSmall,
            ),
        horizontalAlignment = Alignment.Start,
    ) {
        AyoloText(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = Int.MAX_VALUE,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))
        AyoloText(
            modifier = Modifier.align(Alignment.End),
            text = timestamp,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MessageItemPreview() {
    AyoloTheme {
        MessageItem(
            text = "That’s very nice place! you guys made a very good decision. Can’t wait to go on vacation!",
            timestamp = "12:00 PM",
        )
    }
}
