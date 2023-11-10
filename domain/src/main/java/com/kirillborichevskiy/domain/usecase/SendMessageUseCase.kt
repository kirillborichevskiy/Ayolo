package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import java.time.LocalDateTime
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : BaseUseCase<Unit, SendMessageUseCase.Params>() {
    data class Params(val chatId: Int, val text: String)

    override suspend operator fun invoke(params: Params) =
        chatRepository.insertMessage(params.chatId, params.text, LocalDateTime.now())
}
