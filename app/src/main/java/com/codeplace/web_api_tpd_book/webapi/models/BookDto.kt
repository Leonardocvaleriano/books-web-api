package com.codeplace.web_api_tpd_book.webapi.models

data class BookDto(
    val author: String?,
    val currencyCode: String?,
    val id: Int?,
    val isbn: String?,
    val price: Double?,
    val title: String?
)

