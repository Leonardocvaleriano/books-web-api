package com.codeplace.bookswebapi.stateFlow

// Sealed class because we have will have two different types: One hand success and other hand error


sealed interface StateFlow {
    data class Loading(val  loading: Boolean): StateFlow
    data class Success<T>(val  data: T): StateFlow
    data class Error(val  errorMessage: String, val errorCode: String?, val detail: String?, val errorId: String?): StateFlow
}