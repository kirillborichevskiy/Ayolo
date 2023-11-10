package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import com.kirillborichevskiy.domain.usecase.ChatItemSelectedUseCase
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

internal class ChatItemSelectedUseCaseTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var inMemoryRepository: InMemoryRepository

    private lateinit var chatItemSelectedUseCase: ChatItemSelectedUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        chatItemSelectedUseCase = ChatItemSelectedUseCase(inMemoryRepository)
    }

    @Test
    fun `chatItemSelected returns success response`() {
        runBlocking {
            Mockito.`when`(
                inMemoryRepository.selectChat(1),
            ).thenReturn(Unit)

            chatItemSelectedUseCase.invoke(ChatItemSelectedUseCase.Params(chatId = 1))

            Mockito.verify(
                inMemoryRepository,
                Mockito.times(1),
            ).selectChat(1)
        }
    }

    @Test
    fun `chatItemSelected throws exception response`() {
        runBlocking {
            Mockito.`when`(
                inMemoryRepository.selectChat(1),
            ).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)
            chatItemSelectedUseCase.invoke(ChatItemSelectedUseCase.Params(chatId = 1))

            Assert.assertEquals(
                inMemoryRepository.selectChat(1),
                chatItemSelectedUseCase.invoke(ChatItemSelectedUseCase.Params(chatId = 1)),
            )
        }
    }
}
