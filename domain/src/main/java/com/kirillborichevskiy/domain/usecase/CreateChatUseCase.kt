package com.kirillborichevskiy.domain.usecase

interface CreateChatUseCase {
    suspend operator fun invoke(chatName: String)
}
