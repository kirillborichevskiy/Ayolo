package com.kirillborichevskiy.ayolo.usecase

import com.kirillborichevskiy.domain.usecase.impl.DeleteChatsUseCaseImpl
import com.kirillborichevskiy.domain.repository.InMemoryRepository
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

internal class DeleteChatsUseCaseImplTest {

    @get:Rule
    val thrown: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var inMemoryRepository: InMemoryRepository

    private lateinit var deleteChatsUseCaseImpl: DeleteChatsUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        deleteChatsUseCaseImpl = DeleteChatsUseCaseImpl(inMemoryRepository)
    }

    @Test
    fun `deleteChats returns success response`() {
        runBlocking {
            Mockito.`when`(
                inMemoryRepository.deleteChats(),
            ).thenReturn(Unit)

            deleteChatsUseCaseImpl.invoke()

            Mockito.verify(inMemoryRepository, Mockito.times(1)).deleteChats()
        }
    }

    @Test
    fun `deleteChats throws exception response`() {
        runBlocking {
            Mockito.`when`(
                inMemoryRepository.deleteChats(),
            ).thenThrow(DatabaseException(""))

            thrown.expect(DatabaseException::class.java)
            deleteChatsUseCaseImpl.invoke()

            Assert.assertEquals(inMemoryRepository.deleteChats(), deleteChatsUseCaseImpl.invoke())
        }
    }
}
