package com.kirillborichevskiy.ayolo.repository

import com.kirillborichevskiy.ayolo.local.db.ChatDao
import com.kirillborichevskiy.ayolo.local.model.Chat
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.Message
import com.kirillborichevskiy.ayolo.local.repository.ChatRepositoryImpl
import com.kirillborichevskiy.domain.repository.IDispatcherProvider
import com.kirillborichevskiy.domain.util.DatabaseException
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

internal class ChatRepositoryImplTest {

    @Mock
    lateinit var chatDao: ChatDao

    @Mock
    lateinit var iDispatcherProvider: IDispatcherProvider

    private lateinit var chatRepositoryImpl: ChatRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        chatRepositoryImpl = ChatRepositoryImpl(chatDao, iDispatcherProvider)
    }

    @Test
    fun `get all chats returns success response`() {
        Mockito.`when`(
            chatDao.getAllChats(),
        ).thenReturn(
            flowOf(listOf(Chat(ChatInfo(0, ""), listOf()))),
        )

        chatRepositoryImpl.getAllChats()

        Mockito.verify(chatDao, times(1)).getAllChats()
    }

    @Test
    fun `compare database throws error and repositoryImpl handels it`() {
        Mockito.`when`(chatDao.getAllChats()).thenThrow(DatabaseException("hello"))

        chatRepositoryImpl.getAllChats()

        assertEquals((chatRepositoryImpl.getAllChats() as Resource.Error).message, "hello")
    }

    @Test
    fun `compare database models are not same`() {
        val databaseModel = flowOf(listOf<Chat>())
        Mockito.`when`(chatDao.getAllChats()).thenReturn(databaseModel)

        chatRepositoryImpl.getAllChats()

        when (val chats = chatRepositoryImpl.getAllChats()) {
            is Resource.Success -> assertNotEquals(chatDao.getAllChats(), chats.data)
            else -> {}
        }
    }

    @Test(expected = DatabaseException::class)
    fun `get all chats returns no response`() {
        Mockito.`when`(chatDao.getAllChats()).thenThrow(DatabaseException(""))

        chatRepositoryImpl.getAllChats()

        assertEquals(chatDao.getAllChats(), chatRepositoryImpl.getAllChats())
    }

    @Test
    fun `getMessagesById returns success response`() {
        Mockito.`when`(
            chatDao.getChatById(1),
        ).thenReturn(
            flowOf(Chat(ChatInfo(0, ""), listOf())),
        )

        chatRepositoryImpl.getMessagesById(1)

        Mockito.verify(chatDao, times(1)).getChatById(1)
    }

    @Test
    fun `compare getMessagesById throws error and repositoryImpl handels it`() {
        Mockito.`when`(chatDao.getChatById(1)).thenThrow(DatabaseException("hello"))

        chatRepositoryImpl.getMessagesById(1)

        assertEquals((chatRepositoryImpl.getMessagesById(1) as Resource.Error).message, "hello")
    }

    @Test
    fun `compare database models from getMessagesById are not same`() {
        val databaseModel = flowOf(Chat(ChatInfo(0, ""), listOf()))
        Mockito.`when`(chatDao.getChatById(1)).thenReturn(databaseModel)

        chatRepositoryImpl.getMessagesById(1)

        when (val chats = chatRepositoryImpl.getMessagesById(1)) {
            is Resource.Success -> assertNotEquals(chatDao.getChatById(1), chats.data)
            else -> {}
        }
    }

    @Test(expected = DatabaseException::class)
    fun `getMessagesById returns no response`() {
        Mockito.`when`(chatDao.getChatById(1)).thenThrow(DatabaseException(""))

        chatRepositoryImpl.getMessagesById(1)

        assertNotEquals(chatDao.getChatById(1), chatRepositoryImpl.getMessagesById(1))
    }

    @Test
    fun `insertMessage returns success response`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(
                chatDao.insertMessage(Message(0, 0, "", LocalDateTime.MAX)),
            ).thenAnswer { Unit }

            chatRepositoryImpl.insertMessage(0, "", LocalDateTime.MAX)

            Mockito.verify(chatDao, times(1)).insertMessage(Message(0, 0, "", LocalDateTime.MAX))
        }
    }

    @Test
    fun `compare insertMessage models are not same`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(chatDao.insertMessage(Message(0, 0, "", LocalDateTime.MAX))).thenAnswer { Unit }

            chatRepositoryImpl.insertMessage(0, "", LocalDateTime.MAX)

            assertNotEquals(chatDao.insertMessage(Message(0, 0, "", LocalDateTime.MAX)), 2)
        }
    }

    @Test(expected = DatabaseException::class)
    fun `insertMessage throws error`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(chatDao.insertMessage(Message(0, 0, "", LocalDateTime.MAX))).thenThrow(DatabaseException(""))

            chatRepositoryImpl.insertMessage(0, "", LocalDateTime.MAX)

            assertEquals(
                chatDao.insertMessage(Message(0, 0, "", LocalDateTime.MAX)),
                chatRepositoryImpl.insertMessage(0, "", LocalDateTime.MAX),
            )
        }
    }

    @Test
    fun `insertChat returns success response`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(
                chatDao.insertChat(ChatInfo(0, "hello")),
            ).thenReturn(Unit)

            chatRepositoryImpl.insertChat("hello")

            Mockito.verify(chatDao, times(1)).insertChat(ChatInfo(0, "hello"))
        }
    }

    @Test
    fun `compare insertChat models are not same`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(chatDao.insertChat(ChatInfo(0, ""))).thenReturn(Unit)

            chatRepositoryImpl.insertChat("")

            assertNotEquals(chatDao.insertChat(ChatInfo(0, "")), DatabaseException("error"))
        }
    }

    @Test(expected = DatabaseException::class)
    fun `insertChat throws error`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(chatDao.insertChat(ChatInfo(0, ""))).thenThrow(DatabaseException(""))

            chatRepositoryImpl.insertChat("")

            assertEquals(chatDao.insertChat(ChatInfo(0, "")), chatRepositoryImpl.insertChat(""))
        }
    }

    @Test
    fun `getChatCountByName returns success response`() {
        runBlocking {
            Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
            Mockito.`when`(
                chatDao.getChatCountByName("hello"),
            ).thenReturn(1)

            chatRepositoryImpl.getChatNameCount("hello")

            Mockito.verify(chatDao, times(1)).getChatCountByName("hello")
        }
    }

    @Test
    fun `compare database getChatCountByName throws error and repositoryImpl handels it`() {
        Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
        Mockito.`when`(chatDao.getChatCountByName("")).thenThrow(DatabaseException("hello"))

        runBlocking {
            chatRepositoryImpl.getChatNameCount("")

            assertEquals((chatRepositoryImpl.getChatNameCount("") as Resource.Error).message, "hello")
        }
    }

    @Test
    fun `compare getChatCountByName models are not same`() {
        Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
        Mockito.`when`(chatDao.getChatCountByName("")).thenReturn(1)

        runBlocking {
            chatRepositoryImpl.getChatNameCount("")

            when (val chats = chatRepositoryImpl.getChatNameCount("")) {
                is Resource.Success -> assertEquals(chatDao.getChatCountByName(""), chats.data)
                else -> {}
            }
        }
    }

    @Test(expected = DatabaseException::class)
    fun `getChatCountByName throws error`() {
        Mockito.`when`(iDispatcherProvider.io()).thenReturn(Dispatchers.IO)
        Mockito.`when`(chatDao.getChatCountByName("")).thenThrow(DatabaseException(""))

        runBlocking {
            chatRepositoryImpl.getChatNameCount("")

            assertEquals(chatDao.getChatCountByName(""), chatRepositoryImpl.getChatNameCount(""))
        }
    }
}
