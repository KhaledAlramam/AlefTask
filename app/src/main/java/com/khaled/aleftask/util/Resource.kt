package com.khaled.aleftask.util

sealed class Resource<T>{
    object Loading: Resource<Nothing>()
    data class Error(val message: String): Resource<Nothing>()
    data class Success<T>(val data: T): Resource<T>()
}
