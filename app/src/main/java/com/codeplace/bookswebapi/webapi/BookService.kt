package com.codeplace.bookswebapi.webapi

import com.codeplace.bookswebapi.ui.views.bookDetails.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.models.BookDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    // Call the verb using the end point which together with the fun getall,
    // gonna be responsable to get the list of books as gson in the web.

    // End point
    @GET("books")
    suspend fun getBooksList(): Response<List<BookDto>?>

    @GET("book/{id}")
    suspend fun getBook(@Path("id") id:Int):Response<BookDetailslDto>

//    @PATCH("book/{id}")
//    suspend fun updateBook(@Path("id") id:Int, @Body params: BookDto):Response<List<BookDto>?>

    //DELETE
}