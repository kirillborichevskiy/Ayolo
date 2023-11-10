package com.kirillborichevskiy.ayolo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.ChatInfo.Companion.CHATS_INFO_DATABASE_NAME
import com.kirillborichevskiy.ayolo.local.model.ChatInfo.Companion.CHAT_ID_COLUMN_NAME
import com.kirillborichevskiy.ayolo.local.model.ChatInfo.Companion.CHAT_NAME_COLUMN_NAME
import com.kirillborichevskiy.ayolo.local.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ChatDao {
    @Insert
    suspend fun insertChat(chatInfo: ChatInfo)

    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM $CHATS_INFO_DATABASE_NAME WHERE $CHAT_ID_COLUMN_NAME = :chatId")
    fun getChatById(chatId: Int): Flow<Chat>?

    @Query("SELECT * FROM $CHATS_INFO_DATABASE_NAME")
    fun getAllChats(): Flow<List<Chat>>

    @Query("SELECT COUNT(*) FROM $CHATS_INFO_DATABASE_NAME WHERE $CHAT_NAME_COLUMN_NAME = :chatName")
    fun getChatCountByName(chatName: String): Int

    @Query("DELETE FROM $CHATS_INFO_DATABASE_NAME WHERE $CHAT_ID_COLUMN_NAME = :chatId")
    fun deleteChatById(chatId: Int)
}
