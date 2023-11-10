package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.usecase.impl.SendMessageUseCaseImpl
import com.kirillborichevskiy.domain.repository.ChatRepository
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

    private lateinit var sendMessageUseCaseImpl: SendMessageUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sendMessageUseCaseImpl = SendMessageUseCaseImpl(chatRepository)
    }

    @Test
    fun `insertMessage throws exception`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.insertMessage(1, "", LocalDateTime.MAX),
            ).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)

            sendMessageUseCaseImpl.invoke(1, "")

            Assert.assertEquals(
                chatRepository.insertMessage(1, "", LocalDateTime.MAX),
                sendMessageUseCaseImpl.invoke(1, ""),
            )
        }
    }
}
