package com.kirillborichevskiy.domain.usecase

import com.kirillborichevskiy.domain.model.DomainChat
import com.kirillborichevskiy.domain.repository.ChatRepository
import com.kirillborichevskiy.domain.util.DatabaseError
import com.kirillborichevskiy.domain.util.Resource
import com.kirillborichevskiy.domain.util.extension.errorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private typealias DomainChatResourceFlow = Resource<Flow<List<DomainChat>>>

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : BaseUseCase<DomainChatResourceFlow, None>() {
    override suspend operator fun invoke(params: None) =
        when (val chatsResourceFlow = chatRepository.getAllChats()) {
            is Resource.Success ->
                Resource.Success(
                    chatsResourceFlow.data.map { chatList ->
                        chatList.sortedByDescending {
                            it.messages.maxByOrNull { message ->
                                message.timestamp
                            }?.timestamp
                        }
                    },
                )

            is Resource.Error -> DatabaseError(chatsResourceFlow.message ?: String.errorMessage)
        }
}
