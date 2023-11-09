package com.kirillborichevskiy.ayolo.local.db

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

internal object LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? = value?.toEpochSecond(ZoneOffset.UTC)

    @TypeConverter
    fun toLocalDateTime(seconds: Long?): LocalDateTime? =
        seconds?.let { LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC) }
}
