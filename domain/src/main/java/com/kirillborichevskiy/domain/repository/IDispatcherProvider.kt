package com.kirillborichevskiy.domain.repository

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
