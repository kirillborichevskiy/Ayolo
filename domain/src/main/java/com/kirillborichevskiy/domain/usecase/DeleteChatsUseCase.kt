package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import javax.inject.Inject

class DeleteChatsUseCase @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : BaseUseCase<Unit, None>() {
    override suspend fun invoke(params: None) = inMemoryRepository.deleteChats()
}
