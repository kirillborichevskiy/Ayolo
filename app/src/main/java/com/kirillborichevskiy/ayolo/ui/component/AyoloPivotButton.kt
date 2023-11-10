package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirillborichevskiy.ayolo.ui.component.common.AyoloText
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.spacing.spacing
import com.kirillborichevskiy.ayolo.util.extension.empty

@Composable
internal fun AyoloPivotButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = String.empty,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
        disabledContentColor = MaterialTheme.colorScheme.primary,
    ),
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.requiredHeight(MaterialTheme.spacing.extraExtraLarge),
        enabled = enabled,
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
    ) {
        AyoloText(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PivotButtonPreview() {
    AyoloTheme {
        AyoloPivotButton(
            enabled = false,
            text = "Create new chat",
        ) { }
    }
}
