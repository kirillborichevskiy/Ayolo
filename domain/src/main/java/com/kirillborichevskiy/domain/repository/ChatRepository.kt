package com.kirillborichevskiy.domain.repository

import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getAllChats(): Resource<Flow<List<DomainChat>>>
    fun getChatById(chatId: Int): Resource<Flow<DomainChat?>>
    suspend fun insertChat(chatName: String)
}
