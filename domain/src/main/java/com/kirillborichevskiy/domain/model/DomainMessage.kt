package com.kirillborichevskiy.domain.model

import java.time.LocalDateTime

data class DomainMessage(
    val id: Int,
    val text: String,
    val timestamp: LocalDateTime,
)
