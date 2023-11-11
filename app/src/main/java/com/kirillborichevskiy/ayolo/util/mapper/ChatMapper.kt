package com.kirillborichevskiy.ayolo.util.mapper

import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage
import kotlinx.collections.immutable.toPersistentList

internal fun DomainChat.toUiChat(): UiChat {
    val domainMessages = messages.map { message ->
        UiMessage(
            id = message.id,
            text = message.text,
            timestamp = message.timestamp.toAmericanTimeString(),
        )
    }

    return UiChat(
        id = id,
        name = name,
        messages = domainMessages.toPersistentList(),
    )
}

internal fun DomainMessage.toUiMessage(): UiMessage {
    return UiMessage(
        id = id,
        text = text,
        timestamp = timestamp.toAmericanTimeString(),
    )
}
