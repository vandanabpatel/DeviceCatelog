package com.example.devicecatalog.utils

sealed class ApiState<out T> {
    data object Loading : ApiState<Nothing>()

    data class Success<out T>(val data: T) : ApiState<T>()

    data class Error(val errorMsg: String) : ApiState<Nothing>()
}