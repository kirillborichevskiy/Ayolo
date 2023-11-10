package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.usecase.impl.GetChatsUseCaseImpl
import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.repository.ChatRepository
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

internal class GetChatUseCaseImplTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var chatRepository: ChatRepository

    private lateinit var getChatUseCaseImpl: GetChatsUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getChatUseCaseImpl = GetChatsUseCaseImpl(chatRepository)
    }

    @Test
    fun `getAllChats returns success response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.getAllChats(),
            ).thenReturn(
                Resource.Success(flowOf(listOf(DomainChat(0, "", listOf())))),
            )

            getChatUseCaseImpl.invoke()

            Mockito.verify(chatRepository, Mockito.times(1)).getAllChats()
        }
    }

    @Test
    fun `getAllChats returns error response`() {
        runBlocking {
            Mockito.`when`(
                chatRepository.getAllChats(),
            ).thenReturn(
                Resource.Error(DatabaseException("error")),
            )

            getChatUseCaseImpl.invoke()

            Assert.assertEquals(
                (chatRepository.getAllChats() as Resource.Error).message,
                (getChatUseCaseImpl.invoke() as DatabaseError).message,
            )
        }
    }

    @Test
    fun `getAllChats throws exception`() {
        runBlocking {
            Mockito.`when`(chatRepository.getAllChats()).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)

            getChatUseCaseImpl.invoke()

            Assert.assertEquals(chatRepository.getAllChats(), getChatUseCaseImpl.invoke())
        }
    }
}
