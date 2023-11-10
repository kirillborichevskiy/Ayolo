package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.model.DomainMessage
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.GetChatMessagesUseCase
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.DatabaseException
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.flow.flowOf
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

internal class GetChatMessagesUseCaseImplTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var chatRepository: ChatRepository

    private lateinit var getChatMessagesUseCaseImpl: GetChatMessagesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getChatMessagesUseCaseImpl = GetChatMessagesUseCase(chatRepository)
    }

    @Test
    fun `getMessagesById returns success response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.getMessagesById(1),
            ).thenReturn(
                Resource.Success(flowOf(listOf(DomainMessage(0, "", LocalDateTime.MAX)))),
            )

            getChatMessagesUseCaseImpl.invoke(GetChatMessagesUseCase.Params(chatId = 1))

            Mockito.verify(chatRepository, Mockito.times(1)).getMessagesById(1)
        }
    }

    @Test
    fun `getMessagesById returns error response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.getMessagesById(1),
            ).thenReturn(
                Resource.Error(DatabaseException("error")),
            )

            getChatMessagesUseCaseImpl.invoke(GetChatMessagesUseCase.Params(chatId = 1))

            Assert.assertEquals(
                (chatRepository.getMessagesById(1) as Resource.Error).message,
                (getChatMessagesUseCaseImpl.invoke(GetChatMessagesUseCase.Params(chatId = 1)) as DatabaseError).message,
            )
        }
    }

    @Test
    fun `getMessagesById throws exception`() {
        runBlocking {
            Mockito.`when`(chatRepository.getMessagesById(1)).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)

            getChatMessagesUseCaseImpl.invoke(GetChatMessagesUseCase.Params(chatId = 1))

            Assert.assertEquals(
                chatRepository.getMessagesById(1),
                getChatMessagesUseCaseImpl.invoke(GetChatMessagesUseCase.Params(chatId = 1)),
            )
        }
    }
}
