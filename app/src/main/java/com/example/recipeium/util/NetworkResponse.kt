package com.example.recipeium.util

sealed class NetworkResponse<out T> {

    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val exception: Exception) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()

}