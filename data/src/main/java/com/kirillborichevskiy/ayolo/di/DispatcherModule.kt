package com.kirillborichevskiy.ayolo.di

import com.kirillborichevskiy.ayolo.di.annotation.DefaultDispatcher
import com.kirillborichevskiy.ayolo.di.annotation.IoDispatcher
import com.kirillborichevskiy.ayolo.di.annotation.MainDispatcher
import com.kirillborichevskiy.ayolo.local.repository.DispatcherProvider
import com.kirillborichevskiy.domain.repository.IDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    @Singleton
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    fun provideDispatcherProvider(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        @MainDispatcher mainDispatcher: CoroutineDispatcher,
    ): IDispatcherProvider {
        return DispatcherProvider(
            ioDispatcher,
            mainDispatcher,
        )
    }
}
