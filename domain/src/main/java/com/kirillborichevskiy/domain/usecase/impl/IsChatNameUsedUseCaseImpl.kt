package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.IsChatNameUsedUseCase
import com.kirillborichevskiy.domain.util.Resource
import javax.inject.Inject

class IsChatNameUsedUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : IsChatNameUsedUseCase {
    override suspend operator fun invoke(chatName: String): Boolean {
        return when (val chatNameCount = chatRepository.getChatNameCount(chatName)) {
            is Resource.Success -> chatNameCount.data > 0
            else -> false
        }
    }
}
