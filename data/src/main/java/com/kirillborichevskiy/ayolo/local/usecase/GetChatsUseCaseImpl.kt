package com.kirillborichevskiy.ayolo.local.usecase

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.GetChatsUseCase
import javax.inject.Inject

class GetChatsUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : GetChatsUseCase {
    override operator fun invoke() = chatRepository.getAllChats()
}
