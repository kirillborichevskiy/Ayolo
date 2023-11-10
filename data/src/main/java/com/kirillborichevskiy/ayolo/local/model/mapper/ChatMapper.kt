package com.kirillborichevskiy.ayolo.local.model.mapper

import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage

internal fun Chat.toDomainChat(): DomainChat {
    val domainMessages = messages.map { message ->
        DomainMessage(
            id = message.id,
            text = message.text,
            timestamp = message.timestamp,
        )
    }

    return DomainChat(
        id = chatInfo.id,
        name = chatInfo.name,
        messages = domainMessages,
    )
}
