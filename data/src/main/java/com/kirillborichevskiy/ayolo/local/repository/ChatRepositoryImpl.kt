package com.kirillborichevskiy.ayolo.local.repository

import com.kirillborichevskiy.ayolo.di.annotation.IoDispatcher
import com.kirillborichevskiy.ayolo.local.db.ChatDao
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.mapper.toDomainChat
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ChatRepository {

    override fun getAllChats(): Resource<Flow<List<DomainChat>>> =
        try {
            val chatsFlow = chatDao.getAllChats().flowOn(dispatcher).map { chatEntities ->
                chatEntities.map { chatEntity ->
                    chatEntity.toDomainChat()
                }
            }
            Resource.Success(chatsFlow)
        } catch (e: Exception) {
            DatabaseError()
        }

    override fun getChatById(chatId: Int): Resource<Flow<DomainChat?>> =
        try {
            val chatFlow = chatDao.getChatById(chatId)?.flowOn(dispatcher)?.map { chat ->
                chat.toDomainChat()
            } ?: flowOf(null)
            Resource.Success(chatFlow)
        } catch (e: Exception) {
            DatabaseError()
        }

    override suspend fun insertChat(chatName: String) {
        withContext(dispatcher) {
            chatDao.insertChat(ChatInfo(name = chatName))
        }
    }
}
