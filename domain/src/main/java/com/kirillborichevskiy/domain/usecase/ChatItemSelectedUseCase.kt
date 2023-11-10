package com.kirillborichevskiy.domain.usecase

interface ChatItemSelectedUseCase {
    suspend operator fun invoke(chatId: Int)
}
