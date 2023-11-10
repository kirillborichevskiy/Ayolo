package com.kirillborichevskiy.ayolo.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class UiMessage(
    val id: Int,
    val text: String,
    val timestamp: String,
)
