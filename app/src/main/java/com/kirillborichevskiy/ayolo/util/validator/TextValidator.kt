package com.kirillborichevskiy.ayolo.util.validator

private const val CHAT_NAME_ALLOWED_REGEX = "[a-zA-Z0-9 !@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{0,50}"
private const val MESSAGE_TEXT_ALLOWED_REGEX = "[a-zA-Z0-9 !@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{0,255}"

internal object TextValidator {
    fun isValidChatNameInput(
        input: String,
    ): Boolean = input.matches(Regex(CHAT_NAME_ALLOWED_REGEX))

    fun isValidMessageTextInput(
        input: String,
    ): Boolean = input.matches(Regex(MESSAGE_TEXT_ALLOWED_REGEX))
}
