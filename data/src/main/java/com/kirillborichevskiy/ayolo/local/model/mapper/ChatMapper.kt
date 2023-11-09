package com.kirillborichevskiy.ayolo.local.model.mapper

import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.Message
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage

fun DomainChat.toChat(): Chat {
    val chatInfo = ChatInfo(id = this.id, name = this.name)

    val messageList: List<Message> = this.messages.map { domainMessage ->
        Message(
            id = domainMessage.id,
            chatId = this.id,
            text = domainMessage.text,
            timestamp = domainMessage.timestamp,
        )
    }

    return Chat(chatInfo = chatInfo, messages = messageList)
}

fun Chat.toDomainChat(): DomainChat {
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
