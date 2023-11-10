package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : BaseUseCase<Unit, CreateChatUseCase.Params>() {
    data class Params(val chatName: String)

    override suspend operator fun invoke(params: Params) =
        chatRepository.insertChat(params.chatName)
}
