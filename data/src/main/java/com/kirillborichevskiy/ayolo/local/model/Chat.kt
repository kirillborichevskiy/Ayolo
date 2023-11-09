package com.kirillborichevskiy.ayolo.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class Chat(
    @Embedded
    val chatInfo: ChatInfo,
    @Relation(
        parentColumn = ChatInfo.CHAT_ID_COLUMN_NAME,
        entityColumn = Message.MESSAGE_CHAT_ID_COLUMN_NAME,
    )
    val messages: List<Message>,
) {
    companion object {
        fun createWithName(chatName: String) =
            Chat(
                chatInfo = ChatInfo(
                    name = chatName,
                ),
                messages = emptyList(),
            )
    }
}
