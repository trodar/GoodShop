package com.trodar.utils.extensions

fun <T> Set<T>.addOrRemove(element: T): Set<T> {
    return this.toMutableSet().apply {
        if (!add(element)) {
            remove(element)
        }
    }.toSet()
}