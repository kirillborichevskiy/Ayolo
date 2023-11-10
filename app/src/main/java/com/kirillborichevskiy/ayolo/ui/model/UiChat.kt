package com.kirillborichevskiy.ayolo.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiChat(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false,
    val messages: List<UiMessage>,
)
