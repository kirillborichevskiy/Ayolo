package com.kirillborichevskiy.ayolo.ui.screen.messages

import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.ui.model.UiMessage
import com.kirillborichevskiy.ayolo.ui.screen.BaseViewModel
import com.kirillborichevskiy.ayolo.util.extension.persistentMap
import com.kirillborichevskiy.ayolo.util.mapper.toUiMessage
import com.kirillborichevskiy.ayolo.util.validator.TextValidator
import com.kirillborichevskiy.domain.usecase.GetChatMessagesUseCase
import com.kirillborichevskiy.domain.usecase.SendMessageUseCase
import com.kirillborichevskiy.domain.util.Resource
import com.kirillborichevskiy.domain.util.extension.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            sendMessageUseCase(chatId, _messageText.value.trim())
            _messageText.emit(String.empty)
        }
    }

    fun onPreloadMessages(chatId: Int) {
        when (val messagesResource = getChatMessagesUseCase(chatId)) {
            is Resource.Success -> viewModelScope.launch {
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
