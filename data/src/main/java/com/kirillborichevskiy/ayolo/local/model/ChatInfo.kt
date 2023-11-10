package com.kirillborichevskiy.ayolo.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirillborichevskiy.ayolo.local.model.ChatInfo.Companion.CHATS_INFO_DATABASE_NAME

@Entity(tableName = CHATS_INFO_DATABASE_NAME)
internal data class ChatInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CHAT_ID_COLUMN_NAME)
    val id: Int = 0,

    @ColumnInfo(name = CHAT_NAME_COLUMN_NAME)
    val name: String,
) {
    companion object {
        const val CHATS_INFO_DATABASE_NAME = "chats_info"

        const val CHAT_ID_COLUMN_NAME = "id"
        const val CHAT_NAME_COLUMN_NAME = "name"
    }
}
