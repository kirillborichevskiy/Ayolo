package com.kirillborichevskiy.ayolo.util.validator

private const val CHAT_NAME_ALLOWED_REGEX = "[a-zA-Z0-9 !@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{0,50}"

internal object TextValidator {
    fun isValidChatNameInput(
        input: String,
    ): Boolean = input.matches(Regex(CHAT_NAME_ALLOWED_REGEX))
}
