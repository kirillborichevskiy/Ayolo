package com.kirillborichevskiy.domain.model

data class DomainChat(
    val id: Int,
    val name: String,
    val messages: List<DomainMessage>,
)
