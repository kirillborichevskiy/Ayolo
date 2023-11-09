package com.kirillborichevskiy.ayolo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirillborichevskiy.ayolo.R
import com.kirillborichevskiy.ayolo.ui.theme.AyoloTheme
import com.kirillborichevskiy.ayolo.ui.theme.color.ElectricBlue
import com.kirillborichevskiy.ayolo.ui.theme.color.PersianBlue

private val PlaceholderColor = ElectricBlue.copy(alpha = 0.5f)
private val UnfocusedBorderColor = PersianBlue.copy(alpha = 0.5f)
private val SelectionBackgroundColor = PersianBlue.copy(alpha = 0.3f)
private val FocusedBorderColor = PersianBlue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun AyoloInputField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onClearClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isFocused) FocusedBorderColor else UnfocusedBorderColor,
                shape = MaterialTheme.shapes.medium,
            )
            .background(color = Color.Transparent)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.titleSmall,
        singleLine = true,
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null,
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
        ),
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                color = PlaceholderColor,
            )
        },
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedLabelColor = PlaceholderColor,
            cursorColor = PersianBlue,
            focusedTextColor = ElectricBlue,
            unfocusedTextColor = UnfocusedBorderColor,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = SelectionBackgroundColor,
            ),
        ),
        maxLines = 1,
    )
}

@Composable
@Preview(showBackground = true)
private fun SearchBarPreview() {
    AyoloTheme {
        AyoloInputField(
            value = "",
            placeholderText = stringResource(id = R.string.chat_name),
            onValueChange = {},
            onClearClick = {},
        )
    }
}
