package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme

@Composable
internal fun AyoloFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.secondary,
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus_floating),
            contentDescription = null,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AyoloFloatingButtonPreview() {
    AyoloTheme {
        AyoloFloatingButton {}
    }
}
