package com.codeplace.bookswebapi.webapi

import com.codeplace.bookswebapi.ui.views.home.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.models.BookDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {


    @GET("books")
    suspend fun getBooksList(): Response<List<BookDto>?>

    @GET("book/{id}")
    suspend fun getBook(@Path("id") id:Int):Response<BookDetailslDto>

}