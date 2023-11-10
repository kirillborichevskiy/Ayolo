package com.kirillborichevskiy.domain.util

import com.kirillborichevskiy.domain.util.extension.empty

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    open class Error<T>(
        private val exception: Exception,
        val message: String? = exception.message,
    ) : Resource<T>()
}

class DatabaseError<T>(
    message: String = String.empty,
) : Resource.Error<T>(
    exception = DatabaseException(message),
    message = message,
)

class DatabaseException(override val message: String) : RuntimeException()
