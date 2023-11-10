package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import com.kirillborichevskiy.domain.util.DatabaseException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class CreateChatUseCaseTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var chatRepository: ChatRepository

    private lateinit var createChatUseCaseImpl: CreateChatUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        createChatUseCaseImpl = CreateChatUseCase(chatRepository)
    }

    @Test
    fun `insertChat returns success response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.insertChat("name"),
            ).thenReturn(Unit)

            createChatUseCaseImpl.invoke(CreateChatUseCase.Params(chatName = ""))

            Mockito.verify(chatRepository, Mockito.times(1)).insertChat("")
        }
    }

    @Test
    fun `insertChat throws exception response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.insertChat("name"),
            ).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)
            createChatUseCaseImpl.invoke(CreateChatUseCase.Params(chatName = ""))

            Assert.assertEquals(chatRepository.insertChat("name"), createChatUseCaseImpl.invoke(CreateChatUseCase.Params(chatName = "name")))
        }
    }
}
