package com.kirillborichevskiy.ayolo.ui.screen.creator

import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.ui.screen.BaseViewModel
import com.kirillborichevskiy.ayolo.util.validator.TextValidator
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import com.kirillborichevskiy.domain.usecase.IsChatNameUsedUseCase
import com.kirillborichevskiy.domain.util.extension.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateChatViewModel @Inject constructor(
    private val createChatUseCase: CreateChatUseCase,
    private val isChatNameUsedUseCase: IsChatNameUsedUseCase,
) : BaseViewModel() {

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
        val trimmedName = chatName.trim()
        viewModelScope.launch(handler) {
            val isAlreadyUsed = async { isChatNameUsedUseCase.invoke(IsChatNameUsedUseCase.Params(trimmedName)) }
            if (isAlreadyUsed.await()) {
                triggerToastError()
            } else {
                createChatUseCase.invoke(CreateChatUseCase.Params(trimmedName))
            }
        }
    }
}
