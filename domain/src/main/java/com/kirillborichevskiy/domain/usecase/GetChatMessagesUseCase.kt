package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.model.DomainMessage
import com.kirillborichevskiy.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetChatMessagesUseCase {
    operator fun invoke(chatId: Int): Resource<Flow<List<DomainMessage>?>>
}
