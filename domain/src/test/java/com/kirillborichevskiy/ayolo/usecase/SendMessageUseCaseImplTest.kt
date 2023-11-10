package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.SendMessageUseCase
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
import java.time.LocalDateTime

internal class SendMessageUseCaseImplTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var chatRepository: ChatRepository

    private lateinit var sendMessageUseCaseImpl: SendMessageUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sendMessageUseCaseImpl = SendMessageUseCase(chatRepository)
    }

    @Test
    fun `insertMessage throws exception`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.insertMessage(1, "", LocalDateTime.MAX),
            ).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)

            sendMessageUseCaseImpl.invoke(SendMessageUseCase.Params(chatId = 1, text = ""))

            Assert.assertEquals(
                chatRepository.insertMessage(1, "", LocalDateTime.MAX),
                sendMessageUseCaseImpl.invoke(SendMessageUseCase.Params(chatId = 1, text = "")),
            )
        }
    }
}
