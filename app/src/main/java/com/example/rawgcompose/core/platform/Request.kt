package com.example.rawgcompose.core.platform

import com.example.rawgcompose.core.common.Resource
import com.example.rawgcompose.core.exception.Failure
import retrofit2.Call

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T
): Resource<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Resource.Success(transform((response.body() ?: default)))
            false -> Resource.Error(Failure.ServerError(response.code(), response.message()))
        }
    } catch (exception: Throwable) {
        Resource.Error(Failure.CustomError(exception.stackTrace.hashCode(), exception.localizedMessage?:"Error"))
    }
}