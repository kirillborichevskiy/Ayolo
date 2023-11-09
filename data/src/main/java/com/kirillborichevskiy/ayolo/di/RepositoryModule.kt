package com.kirillborichevskiy.ayolo.di

import com.kirillborichevskiy.ayolo.local.repository.ChatRepositoryImpl
import com.kirillborichevskiy.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository
}
