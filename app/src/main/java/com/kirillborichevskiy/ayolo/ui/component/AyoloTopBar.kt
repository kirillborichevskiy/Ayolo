package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.domain.util.extension.empty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AyoloTopBar(
    modifier: Modifier = Modifier,
    title: String = String.empty,
    startComposable: @Composable (() -> Unit)? = null,
    endComposable: @Composable (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = { startComposable?.let { it() } },
        title = {
            AyoloText(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
            )
        },
        actions = { endComposable?.let { it() } },
    )
}

@Preview
@Composable
private fun AyoloTopBarPreview() {
    AyoloTheme {
        AyoloTopBar(title = "Recent chats")
    }
}
