package com.example.rawgcompose.core.common

sealed class Resource<out E, out T>(val error: E? = null, val data: T? = null) {
    class Success<T>(data: T) : Resource<Nothing, T>(data = data)
    class Error<E>(error: E) : Resource<E, Nothing>(error = error)
    class Loading : Resource<Nothing, Nothing>()
}
