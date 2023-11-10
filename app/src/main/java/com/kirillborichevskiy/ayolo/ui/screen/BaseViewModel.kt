package com.kirillborichevskiy.ayolo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _shouldShowToastError = Channel<Unit>()
    val shouldShowToastError: Flow<Unit> = _shouldShowToastError.receiveAsFlow()

    val handler = CoroutineExceptionHandler { _, _ ->
        triggerToastError()
    }

    protected fun triggerToastError() {
        viewModelScope.launch {
            _shouldShowToastError.send(Unit)
        }
    }
}
