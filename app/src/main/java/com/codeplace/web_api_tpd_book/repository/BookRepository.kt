package com.codeplace.web_api_tpd_book.repository
import com.codeplace.web_api_tpd_book.webapi.RetrofitInitializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

 class BookRepository(
     ) {
    suspend fun getBooksList() = withContext(Dispatchers.IO){

        val api = RetrofitInitializer().bookService
        return@withContext api.getBooksList()
    }
}