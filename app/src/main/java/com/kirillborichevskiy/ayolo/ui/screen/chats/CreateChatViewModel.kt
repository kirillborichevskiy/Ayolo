package com.kirillborichevskiy.ayolo.ui.screen.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.util.validator.TextValidator
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import com.kirillborichevskiy.domain.util.extension.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateChatViewModel @Inject constructor(
    private val createChatUseCase: CreateChatUseCase,
) : ViewModel() {

    private val _chatName = MutableStateFlow(String.empty)
    val chatName = _chatName.asStateFlow()

    fun onChatNameChange(newValue: String) {
        if (TextValidator.isValidChatNameInput(newValue)) {
            _chatName.value = newValue
        }
    }

    fun onClearChatName() {
        _chatName.value = String.empty
    }

    fun onCreateNewChat(chatName: String) {
        viewModelScope.launch {
            createChatUseCase(chatName)
        }
    }
}
