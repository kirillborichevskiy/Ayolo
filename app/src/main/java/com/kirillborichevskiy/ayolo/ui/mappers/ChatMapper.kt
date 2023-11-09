package com.kirillborichevskiy.ayolo.ui.mappers

import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.util.mapper.toAmericanTimeString
import com.kirillborichevskiy.ayolo.util.mapper.toLocalDateTime
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage

fun DomainChat.toUiChat(): UiChat {
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
        messages = domainMessages,
    )
}

fun UiChat.toDomainChat(): DomainChat {
    val uiMessages = messages.map { message ->
        DomainMessage(
            id = message.id,
            text = message.text,
            timestamp = message.timestamp.toLocalDateTime(),
        )
    }

    return DomainChat(
        id = id,
        name = name,
        messages = uiMessages,
    )
}
