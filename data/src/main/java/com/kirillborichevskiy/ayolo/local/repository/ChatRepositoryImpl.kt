package com.kirillborichevskiy.ayolo.local.repository

import com.kirillborichevskiy.ayolo.local.db.ChatDao
import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.Message
import com.kirillborichevskiy.ayolo.local.model.mapper.toDomainChat
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.model.DomainMessage
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.repository.IDispatcherProvider
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.Resource
import com.kirillborichevskiy.domain.util.extension.empty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val dispatcherProvider: IDispatcherProvider,
) : ChatRepository {

    override fun getAllChats(): Resource<Flow<List<DomainChat>>> =
        try {
            val chatsFlow = chatDao.getAllChats().flowOn(dispatcherProvider.io()).map {
                it.map(Chat::toDomainChat)
            }
            Resource.Success(chatsFlow)
        } catch (e: Exception) {
            DatabaseError(e.message ?: String.empty)
        }

    override fun getMessagesById(chatId: Int): Resource<Flow<List<DomainMessage>?>> =
        try {
            val chatFlow = chatDao.getChatById(chatId)?.flowOn(dispatcherProvider.io())?.map { chat ->
                chat.toDomainChat().messages
            } ?: flowOf(null)
            Resource.Success(chatFlow)
        } catch (e: Exception) {
            DatabaseError(e.message ?: String.empty)
        }

    override suspend fun getChatNameCount(chatName: String): Resource<Int> =
        withContext(dispatcherProvider.io()) {
            try {
                val chatCount = chatDao.getChatCountByName(chatName)
                Resource.Success(chatCount)
            } catch (e: Exception) {
                DatabaseError(e.message ?: String.empty)
            }
        }

    override suspend fun insertChat(chatName: String) {
        withContext(dispatcherProvider.io()) {
            chatDao.insertChat(ChatInfo(name = chatName))
        }
    }

    override suspend fun insertMessage(chatId: Int, text: String, timestamp: LocalDateTime) {
        withContext(dispatcherProvider.io()) {
            chatDao.insertMessage(Message(chatId = chatId, text = text, timestamp = timestamp))
        }
    }
}
