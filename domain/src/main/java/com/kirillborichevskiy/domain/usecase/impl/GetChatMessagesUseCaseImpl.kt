package com.kirillborichevskiy.domain.usecase.impl

import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.usecase.GetChatMessagesUseCase
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.Resource
import com.kirillborichevskiy.domain.util.extension.empty
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetChatMessagesUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository,
) : GetChatMessagesUseCase {
    override operator fun invoke(chatId: Int) =
        when (val messagesResourceFlow = chatRepository.getMessagesById(chatId)) {
            is Resource.Success ->
                Resource.Success(
                    messagesResourceFlow.data.map { messageList ->
                        messageList?.reversed()
                    },
                )

            is Resource.Error -> DatabaseError(messagesResourceFlow.message ?: String.empty)
        }
}
