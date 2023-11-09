package com.kirillborichevskiy.ayolo.util.extension

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf

internal fun <T, R> List<T>.persistentMap(transform: (T) -> R): PersistentList<R> {
    return persistentListOf<R>().mutate {
        for (item in this) {
            it.add(transform(item))
        }
    }
}
