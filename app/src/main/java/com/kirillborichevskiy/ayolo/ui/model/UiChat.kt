package com.kirillborichevskiy.ayolo.ui.model

import com.kirillborichevskiy.domain.util.extension.empty

data class UiChat(
    val id: Int,
    val name: String,
    val messages: List<UiMessage>,
) {
    companion object {
        val empty: UiChat
            get() = UiChat(id = 0, name = String.empty, messages = emptyList())
    }
}
