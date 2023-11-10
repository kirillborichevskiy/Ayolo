package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.InMemoryRepository
import javax.inject.Inject

class ChatItemSelectedUseCase @Inject constructor(
    private val inMemoryRepository: InMemoryRepository,
) : BaseUseCase<Unit, ChatItemSelectedUseCase.Params>() {
    data class Params(val chatId: Int)
    override suspend fun invoke(params: Params) {
        val selectedIds = inMemoryRepository.getSelectedIds()
        if (selectedIds.contains(params.chatId)) {
            inMemoryRepository.unselectChat(params.chatId)
        } else {
            inMemoryRepository.selectChat(params.chatId)
        }
    }
}
