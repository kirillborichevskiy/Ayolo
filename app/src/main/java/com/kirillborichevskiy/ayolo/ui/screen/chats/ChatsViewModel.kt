package com.kirillborichevskiy.ayolo.ui.screen.chats

import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.ui.screen.BaseViewModel
import com.kirillborichevskiy.ayolo.util.extension.persistentMap
import com.kirillborichevskiy.ayolo.util.mapper.toUiChat
import com.kirillborichevskiy.domain.usecase.ChatItemSelectedUseCase
import com.kirillborichevskiy.domain.usecase.DeleteChatsUseCase
import com.kirillborichevskiy.domain.usecase.GetChatsUseCase
import com.kirillborichevskiy.domain.usecase.GetSelectedIdsUseCase
import com.kirillborichevskiy.domain.usecase.None
import com.kirillborichevskiy.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val chatItemSelectedUseCase: ChatItemSelectedUseCase,
    private val deleteChatsUseCase: DeleteChatsUseCase,
    private val getSelectedIdsUseCase: GetSelectedIdsUseCase,
) : BaseViewModel() {

    private val _chats = MutableStateFlow(persistentListOf<UiChat>())
    val chats = _chats.asStateFlow()

    private val _isSelected = MutableStateFlow(false)
    val isSelected = _isSelected.asStateFlow()

    private val _isChatsLoading = MutableStateFlow(true)
    val isChatsLoading = _isChatsLoading.asStateFlow()

    init {
        getAllChats()
    }

    private fun getAllChats() {
        viewModelScope.launch(handler) {
            when (val chatsResource = getChatsUseCase.invoke(None)) {
                is Resource.Success -> viewModelScope.launch {
                    chatsResource.data.collect { domainChatList ->
                        _chats.emit(
                            domainChatList.persistentMap { domainChat ->
                                domainChat.toUiChat()
                            },
                        )
                        delay(SPLASH_SCREEN_DELAY)
                        _isChatsLoading.emit(false)
                    }
                }

                is Resource.Error -> triggerToastError()
            }
        }
    }

    fun onLongChatsClick(chatId: Int) {
        viewModelScope.launch(handler) {
            chatItemSelectedUseCase.invoke(ChatItemSelectedUseCase.Params(chatId))
            val selectedIds = getSelectedIdsUseCase.invoke(None)
            _chats.emit(
                _chats.value.map { chat ->
                    chat.copy(isSelected = selectedIds.contains(chat.id))
                }.toPersistentList(),
            )
            _isSelected.emit(_chats.value.any { it.isSelected })
        }
    }

    fun onDeleteClick() = viewModelScope.launch(handler) {
        deleteChatsUseCase.invoke(None)
        _isSelected.emit(false)
    }

    private companion object {
        const val SPLASH_SCREEN_DELAY = 1500L
    }
}
