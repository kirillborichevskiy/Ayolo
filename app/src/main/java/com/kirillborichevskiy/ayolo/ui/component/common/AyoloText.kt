package com.kirillborichevskiy.ayolo.ui.component.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.util.extension.empty

@Composable
internal fun AyoloText(
    modifier: Modifier = Modifier,
    text: String = String.empty,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    maxLines: Int = 1,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
    )
}

@Composable
@Preview(showBackground = true)
private fun AyoloTextPreview() {
    AyoloTheme {
        AyoloText(
            text = stringResource(R.string.specify_chat_name),
        )
    }
}
