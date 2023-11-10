package com.kirillborichevskiy.domain.repository

import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ChatRepository {
    fun getAllChats(): Resource<Flow<List<DomainChat>>>
    fun getMessagesById(chatId: Int): Resource<Flow<List<DomainMessage>?>>
    suspend fun getChatNameCount(chatName: String): Resource<Int>
    suspend fun insertChat(chatName: String)
    suspend fun insertMessage(chatId: Int, text: String, timestamp: LocalDateTime)
}
