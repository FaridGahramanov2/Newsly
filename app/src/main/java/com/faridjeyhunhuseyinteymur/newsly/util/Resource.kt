package com.faridjeyhunhuseyinteymur.newsly.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // TODO: Will be used in Part 2
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>: Resource<T>()
}