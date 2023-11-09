package com.kirillborichevskiy.ayolo.local.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import javax.inject.Inject

class CreateChatUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : CreateChatUseCase {
    override suspend operator fun invoke(chatName: String) =
        chatRepository.insertChat(chatName)
}
