package com.kirillborichevskiy.ayolo.util.mapper

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TIME_FORMAT_PATTERN = "h:mma"

fun LocalDateTime.toAmericanTimeString(): String {
    val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN, Locale.US)
    return this.format(formatter)
}

fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN, Locale.US)
    return LocalDateTime.parse(this, formatter)
}
