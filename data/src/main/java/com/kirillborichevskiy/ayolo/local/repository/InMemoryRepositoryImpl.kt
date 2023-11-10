package com.kirillborichevskiy.ayolo.local.repository

import com.kirillborichevskiy.ayolo.local.db.ChatDao
import com.kirillborichevskiy.domain.repository.IDispatcherProvider
import com.kirillborichevskiy.domain.repository.InMemoryRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class InMemoryRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val dispatcherProvider: IDispatcherProvider,
) : InMemoryRepository {

    private val list: MutableList<Int> = mutableListOf()

    override suspend fun selectChat(chatId: Int) {
        list.add(chatId)
    }

    override suspend fun unselectChat(chatId: Int) {
        list.remove(chatId)
    }

    override suspend fun deleteChats() {
        withContext(dispatcherProvider.io()) {
            list.forEach(chatDao::deleteChatById)
        }
    }

    override fun getSelectedIds(): List<Int> = list
}
