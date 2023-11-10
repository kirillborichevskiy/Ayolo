package com.kirillborichevskiy.domain.repository

interface InMemoryRepository {
    suspend fun selectChat(chatId: Int)
    suspend fun unselectChat(chatId: Int)
    suspend fun deleteChats()
    fun getSelectedIds(): List<Int>
}
