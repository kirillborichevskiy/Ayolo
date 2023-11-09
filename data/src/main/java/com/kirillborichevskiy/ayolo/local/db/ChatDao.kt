package com.kirillborichevskiy.ayolo.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.ChatInfo.Companion.CHATS_INFO_DATABASE_NAME
import com.kirillborichevskiy.ayolo.local.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert
    suspend fun insertChat(chatInfo: ChatInfo)

    @Insert
    suspend fun insertMessages(messages: List<Message>)

    @Query("SELECT * FROM $CHATS_INFO_DATABASE_NAME WHERE id = :chatId")
    fun getChatById(chatId: Int): Flow<Chat>?

    @Query("SELECT * FROM $CHATS_INFO_DATABASE_NAME")
    fun getAllChats(): Flow<List<Chat>>
}
