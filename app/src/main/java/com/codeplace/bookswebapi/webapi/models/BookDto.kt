package com.codeplace.bookswebapi.webapi.models

data class BookDto(
    val id: Int?,
    val title: String?,
    val author: String?,
    val isbn: String?,
    val price: Double?,
    val currencyCode: String?,
    //val description: String?
)