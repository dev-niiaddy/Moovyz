package com.orbilax.moovyz.util

sealed class DataResult<out T: Any> {

    data class Success<out T: Any>(val data: T): DataResult<T>()
    data class Error(val error: Exception): DataResult<Nothing>()
    object InProgress: DataResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success [rem_data=$data]"
            is Error -> "Error [error=${error.localizedMessage}]"
            is InProgress -> "In Progress"
        }
    }
}