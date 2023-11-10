package com.kirillborichevskiy.domain.usecase

interface SendMessageUseCase {
    suspend operator fun invoke(chatId: Int, text: String)
}
