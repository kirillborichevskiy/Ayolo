package com.kirillborichevskiy.ayolo.di

import com.kirillborichevskiy.ayolo.local.usecase.CreateChatUseCaseImpl
import com.kirillborichevskiy.ayolo.local.usecase.GetChatsUseCaseImpl
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import com.kirillborichevskiy.domain.usecase.GetChatsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindCreateChatUseCase(createChatUseCase: CreateChatUseCaseImpl): CreateChatUseCase

    @Binds
    fun bindGetChatsUseCase(getChatsUseCase: GetChatsUseCaseImpl): GetChatsUseCase
}
