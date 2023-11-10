package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import com.kirillborichevskiy.domain.usecase.ChatItemSelectedUseCase
import javax.inject.Inject

class ChatItemSelectedUseCaseImpl @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : ChatItemSelectedUseCase {
    override suspend fun invoke(chatId: Int) {
        val selectedIds = inMemoryRepository.getSelectedIds()
        if (selectedIds.contains(chatId)) {
            inMemoryRepository.unselectChat(chatId)
        } else {
            inMemoryRepository.selectChat(chatId)
        }
    }
}
