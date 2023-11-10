package com.kirillborichevskiy.ayolo.ui.screen.messages

import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.ui.screen.BaseViewModel
import com.kirillborichevskiy.ayolo.util.extension.empty
import com.kirillborichevskiy.ayolo.util.extension.persistentMap
import com.kirillborichevskiy.ayolo.util.mapper.toUiMessage
import com.kirillborichevskiy.ayolo.util.validator.TextValidator
import com.kirillborichevskiy.domain.usecase.GetChatMessagesUseCase
import com.kirillborichevskiy.domain.usecase.SendMessageUseCase
import com.kirillborichevskiy.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class MessagesViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getChatMessagesUseCase: GetChatMessagesUseCase,
) : BaseViewModel() {
    private val _messages = MutableStateFlow(persistentListOf<UiMessage>())
    val messages = _messages.asStateFlow()

    private val _messageText = MutableStateFlow(String.empty)
    val messageText = _messageText.asStateFlow()

    fun onMessageTextChange(newValue: String) {
        if (TextValidator.isValidMessageTextInput(newValue)) {
            _messageText.value = newValue
        }
    }

    fun onClearMessageText() {
        _messageText.value = String.empty
    }

    fun onSendNewMessage(chatId: Int) {
        viewModelScope.launch(handler) {
            sendMessageUseCase.invoke(
                SendMessageUseCase.Params(
                    chatId = chatId,
                    text = _messageText.value.trim(),
                ),
            )
            _messageText.emit(String.empty)
        }
    }

    fun onPreloadMessages(chatId: Int) {
        viewModelScope.launch(handler) {
            when (val messagesResource = getChatMessagesUseCase.invoke(GetChatMessagesUseCase.Params(chatId))) {
                is Resource.Success -> withContext(handler) {
                    messagesResource.data.collect { domainMessages ->
                        domainMessages?.let {
                            _messages.emit(
                                it.persistentMap { domainMessage ->
                                    domainMessage.toUiMessage()
                                },
                            )
                        }
                    }
                }

                is Resource.Error -> triggerToastError()
            }
        }
    }
}
