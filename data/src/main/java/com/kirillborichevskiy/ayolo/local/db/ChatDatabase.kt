package com.kirillborichevskiy.ayolo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kirillborichevskiy.ayolo.local.model.ChatInfo
import com.kirillborichevskiy.ayolo.local.model.Message

@Database(
    entities = [ChatInfo::class, Message::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class ChatDatabase : RoomDatabase() {
    abstract val chatDao: ChatDao

    companion object {
        const val DATABASE_NAME = "chat_database"
    }
}
