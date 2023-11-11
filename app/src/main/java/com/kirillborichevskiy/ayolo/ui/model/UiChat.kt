package com.kirillborichevskiy.ayolo.ui.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class UiChat(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false,
    val messages: ImmutableList<UiMessage>,
)
