package com.kirillborichevskiy.domain.util.extension

inline val String.Companion.errorMessage: String get() = "Something went wrong"

inline val String.Companion.empty: String get() = ""
