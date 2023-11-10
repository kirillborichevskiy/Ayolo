package com.kirillborichevskiy.domain.usecase

interface IsChatNameUsedUseCase {
    suspend operator fun invoke(chatName: String): Boolean
}
