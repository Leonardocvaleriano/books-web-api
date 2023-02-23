package com.codeplace.bookswebapi.ui.views.bookDetails.models

data class BookDetailslDto(
    val author: String?,
    val currencyCode: String?,
    val description: String?,
    val id: Int?,
    val isbn: String?,
    val price: Double?,
    val title: String?
)