package com.example.colormemory.utils

data class Resource<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> matched(data: T): Resource<T>? =
            Resource(
                status = Status.MATCHED,
                data = data
            )

        fun <T> unmatched(data: T): Resource<T>? =
            Resource(
                status = Status.UNMATCHED,
                data = data
            )
    }
}