package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.util.Resource
import javax.inject.Inject

class IsChatNameUsedUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : BaseUseCase<Boolean, IsChatNameUsedUseCase.Params>() {
    data class Params(val chatName: String)

    override suspend operator fun invoke(params: Params): Boolean {
        return when (val chatNameCount = chatRepository.getChatNameCount(params.chatName)) {
            is Resource.Success -> chatNameCount.data > 0
            else -> false
        }
    }
}
