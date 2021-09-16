package com.example.rawgcompose.core.platform

import com.example.rawgcompose.core.common.Resource
import retrofit2.Call
import retrofit2.HttpException

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T
): Resource<R> {
    return try {
        val response = call.execute()
        when(response.isSuccessful) {
            true -> Resource.Success(transform(response.body() ?: default))
            false -> Resource.Error(response.message() ?: "Error in response")
        }
    } catch (e: HttpException) {
        Resource.Error(e.localizedMessage ?: "Server Error")
    }
    catch (exception: Throwable) {
        Resource.Error(exception.localizedMessage ?: "Exception")
    }
}