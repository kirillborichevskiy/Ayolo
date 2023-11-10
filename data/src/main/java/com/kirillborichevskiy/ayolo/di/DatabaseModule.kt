package com.kirillborichevskiy.ayolo.di

import android.content.Context
import androidx.room.Room
import com.kirillborichevskiy.ayolo.local.db.ChatDao
import com.kirillborichevskiy.ayolo.local.db.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            ChatDatabase.DATABASE_NAME,
        ).build()

    @Provides
    @Singleton
    fun provideChatDao(database: ChatDatabase): ChatDao = database.chatDao
}
