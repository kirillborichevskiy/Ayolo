package com.kirillborichevskiy.ayolo.local.repository

import com.kirillborichevskiy.ayolo.di.annotation.IoDispatcher
import com.kirillborichevskiy.ayolo.di.annotation.MainDispatcher
import com.kirillborichevskiy.domain.repository.IDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class DispatcherProvider @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : IDispatcherProvider {
    override fun io() = ioDispatcher
    override fun main() = mainDispatcher
}
