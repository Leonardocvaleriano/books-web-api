package com.codeplace.bookswebapi.ui.views.home.models

data class BookDto(
    val id: Int?,
    val title: String?,
    val author: String?,
    val isbn: String?,
    val price: Double?,
    val currencyCode: String?,
)