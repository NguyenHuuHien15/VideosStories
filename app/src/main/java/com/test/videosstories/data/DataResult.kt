package com.test.videosstories.data

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Failure<out T>(val errorType: ErrorType, val errorCode: Int = 0) : DataResult<T>()
}

// A remplir/modifier/maj plus tard
enum class ErrorType {
    UNKNOWN,
    NETWORK,
    IO,
    INPUT,
}