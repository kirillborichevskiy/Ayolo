package com.kirillborichevskiy.ayolo.di

import com.kirillborichevskiy.domain.usecase.ChatItemSelectedUseCase
import com.kirillborichevskiy.domain.usecase.CreateChatUseCase
import com.kirillborichevskiy.domain.usecase.DeleteChatsUseCase
import com.kirillborichevskiy.domain.usecase.GetChatMessagesUseCase
import com.kirillborichevskiy.domain.usecase.GetChatsUseCase
import com.kirillborichevskiy.domain.usecase.GetSelectedIdsUseCase
import com.kirillborichevskiy.domain.usecase.IsChatNameUsedUseCase
import com.kirillborichevskiy.domain.usecase.SendMessageUseCase
import com.kirillborichevskiy.domain.usecase.impl.ChatItemSelectedUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.CreateChatUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.DeleteChatsUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.GetChatMessagesUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.GetChatsUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.GetSelectedIdsUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.IsChatNameUsedUseCaseImpl
import com.kirillborichevskiy.domain.usecase.impl.SendMessageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {
    @Binds
    fun bindCreateChatUseCase(createChatUseCase: CreateChatUseCaseImpl): CreateChatUseCase

    @Binds
    fun bindGetChatsUseCase(getChatsUseCase: GetChatsUseCaseImpl): GetChatsUseCase

    @Binds
    fun bindGetChatMessagesUseCase(getChatsUseCase: GetChatMessagesUseCaseImpl): GetChatMessagesUseCase

    @Binds
    fun bindSendMessageUseCase(getChatsUseCase: SendMessageUseCaseImpl): SendMessageUseCase

    @Binds
    fun bindIsChatNameUsedUseCase(getChatsUseCase: IsChatNameUsedUseCaseImpl): IsChatNameUsedUseCase

    @Binds
    fun bindChatItemSelectedUseCase(chatItemSelectedUseCaseImpl: ChatItemSelectedUseCaseImpl): ChatItemSelectedUseCase

    @Binds
    fun bindDeleteChatsUseCase(deleteChatsUseCaseImpl: DeleteChatsUseCaseImpl): DeleteChatsUseCase

    @Binds
    fun bindGetSelectedIdsUseCase(getSelectedIdsUseCase: GetSelectedIdsUseCaseImpl): GetSelectedIdsUseCase
}
