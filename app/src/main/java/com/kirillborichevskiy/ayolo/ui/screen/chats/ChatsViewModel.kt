package com.kirillborichevskiy.ayolo.ui.screen.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirillborichevskiy.ayolo.ui.mappers.toUiChat
import com.kirillborichevskiy.ayolo.ui.model.UiChat
import com.kirillborichevskiy.ayolo.util.extension.persistentMap
import com.kirillborichevskiy.domain.usecase.GetChatsUseCase
import com.kirillborichevskiy.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
) : ViewModel() {

    private val _chats = MutableStateFlow(persistentListOf<UiChat>())
    val chats = _chats.asStateFlow()

    init {
        getAllChats()
    }

    private fun getAllChats() {
        when (val chatsResource = getChatsUseCase()) {
            is Resource.Success -> viewModelScope.launch {
                chatsResource.data.collect { domainChatList ->
                    _chats.emit(
                        domainChatList.persistentMap { domainChat ->
                            domainChat.toUiChat()
                        },
                    )
                }
            }

            is Resource.Error -> {}
        }
    }
}
