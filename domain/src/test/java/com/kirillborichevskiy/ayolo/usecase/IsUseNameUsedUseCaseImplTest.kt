package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.usecase.impl.IsChatNameUsedUseCaseImpl
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.util.DatabaseException
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class IsUseNameUsedUseCaseImplTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var chatRepository: ChatRepository

    private lateinit var isChatNameUsedUseCaseImpl: IsChatNameUsedUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        isChatNameUsedUseCaseImpl = IsChatNameUsedUseCaseImpl(chatRepository)
    }

    @Test
    fun `getChatNameCount returns success response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.getChatNameCount("name"),
            ).thenReturn(
                Resource.Success(1),
            )

            isChatNameUsedUseCaseImpl.invoke("")

            Mockito.verify(chatRepository, Mockito.times(1)).getChatNameCount("")
        }
    }

    @Test
    fun `check chat name exist`() {
        val databaseModel = Resource.Success(1)
        runBlocking {
            Mockito.`when`(chatRepository.getChatNameCount("")).thenReturn(databaseModel)

            isChatNameUsedUseCaseImpl.invoke("")

            when (val chats = chatRepository.getChatNameCount("")) {
                is Resource.Success -> Assert.assertEquals(isChatNameUsedUseCaseImpl.invoke(""), chats.data > 0)
                else -> {}
            }
        }
    }

    @Test
    fun `check chat name not exist`() {
        val databaseModel = Resource.Error<Exception>(DatabaseException(""))
        runBlocking {
            Mockito.`when`(
                chatRepository.getChatNameCount(""),
            ).thenAnswer { databaseModel }

            isChatNameUsedUseCaseImpl.invoke("")

            when (chatRepository.getChatNameCount("")) {
                is Resource.Error -> Assert.assertEquals(isChatNameUsedUseCaseImpl.invoke(""), false)
                else -> {}
            }
        }
    }

    @Test
    fun `getChatNameCount throws exception`() {
        runBlocking {
            Mockito.`when`(chatRepository.getChatNameCount("")).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)

            isChatNameUsedUseCaseImpl.invoke("")

            Assert.assertEquals(chatRepository.getChatNameCount(""), isChatNameUsedUseCaseImpl.invoke(""))
        }
    }
}
