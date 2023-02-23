package com.codeplace.bookswebapi.repository
import com.codeplace.bookswebapi.webapi.RetrofitInitializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

 class BooksRepository(
     ) {
    suspend fun getBooksList() = withContext(Dispatchers.IO){
        val api = RetrofitInitializer().bookService
        return@withContext api.getBooksList()
    }
 }