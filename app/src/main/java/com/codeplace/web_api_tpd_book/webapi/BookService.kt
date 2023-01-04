package com.codeplace.web_api_tpd_book.webapi

import com.codeplace.web_api_tpd_book.webapi.models.BookDto
import retrofit2.Response
import retrofit2.http.GET

interface BookService {

    // Call the verb using the end point which together with the fun getall,
    // gonna be responsable to get the list of books as gson in the web.

    // End point
    @GET("books")
    suspend fun getBooksList(): Response<List<BookDto>?>

}