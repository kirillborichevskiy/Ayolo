package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.model.DomainMessage
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.Resource
import com.kirillborichevskiy.domain.util.extension.errorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private typealias DomainMessageResourceFlow = Resource<Flow<List<DomainMessage>?>>

class GetChatMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : BaseUseCase<DomainMessageResourceFlow, GetChatMessagesUseCase.Params>() {
    data class Params(val chatId: Int)

    override suspend operator fun invoke(params: Params) =
        when (val messagesResourceFlow = chatRepository.getMessagesById(params.chatId)) {
            is Resource.Success ->
                Resource.Success(
                    messagesResourceFlow.data.map { messageList ->
                        messageList?.reversed()
                    },
                )

            is Resource.Error -> DatabaseError(messagesResourceFlow.message ?: String.errorMessage)
        }
}
