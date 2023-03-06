package com.codeplace.bookswebapi.ui.views.home.models

import org.json.JSONObject
import java.io.Serializable

data class BookDetailslDto(
    var author: String,
    var currencyCode: String,
    var description: String,
    val id: Int,
    var isbn: String,
    var price: Double,
    var title: String
)