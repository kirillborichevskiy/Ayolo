package com.kirillborichevskiy.ayolo.local.model

import androidx.room.Embedded
import androidx.room.Relation

internal data class Chat(
    @Embedded
    val chatInfo: ChatInfo,
    @Relation(
        parentColumn = ChatInfo.CHAT_ID_COLUMN_NAME,
        entityColumn = Message.MESSAGE_CHAT_ID_COLUMN_NAME,
    )
    val messages: List<Message>,
)
