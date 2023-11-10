package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import com.kirillborichevskiy.domain.usecase.DeleteChatsUseCase
import javax.inject.Inject

class DeleteChatsUseCaseImpl @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : DeleteChatsUseCase {
    override suspend fun invoke() = inMemoryRepository.deleteChats()
}
