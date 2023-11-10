package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.SendMessageUseCase
import java.time.LocalDateTime
import javax.inject.Inject

class SendMessageUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : SendMessageUseCase {
    override suspend operator fun invoke(chatId: Int, text: String) =
        chatRepository.insertMessage(chatId, text, LocalDateTime.now())
}
