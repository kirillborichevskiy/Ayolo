package com.kirillborichevskiy.ayolo.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kirillborichevskiy.ayolo.local.model.Message.Companion.MESSAGES_DATABASE_NAME
import com.kirillborichevskiy.ayolo.local.model.Message.Companion.MESSAGE_CHAT_ID_COLUMN_NAME
import java.time.LocalDateTime

@Entity(
    tableName = MESSAGES_DATABASE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ChatInfo::class,
            parentColumns = [ChatInfo.CHAT_ID_COLUMN_NAME],
            childColumns = [MESSAGE_CHAT_ID_COLUMN_NAME],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MESSAGE_ID_COLUMN_NAME)
    val id: Int = 0,

    @ColumnInfo(name = MESSAGE_CHAT_ID_COLUMN_NAME)
    val chatId: Int,

    @ColumnInfo(name = MESSAGE_TEXT_COLUMN_NAME)
    val text: String,

    @ColumnInfo(name = MESSAGE_TIMESTAMP_COLUMN_NAME)
    val timestamp: LocalDateTime,
) {
    companion object {
        const val MESSAGES_DATABASE_NAME = "messages"

        const val MESSAGE_ID_COLUMN_NAME = "id"
        const val MESSAGE_CHAT_ID_COLUMN_NAME = "chatId"
        const val MESSAGE_TEXT_COLUMN_NAME = "text"
        const val MESSAGE_TIMESTAMP_COLUMN_NAME = "timestamp"
    }
}
