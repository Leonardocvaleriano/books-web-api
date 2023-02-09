//package com.codeplace.web_api_tpd_book.webapi
//
//import android.util.Log
//import com.codeplace.web_api_tpd_book.webapi.models.BookDto
//
//private const val TAG = "BookWebClient"
//
//class BookWebClient{
//
//    private val bookService:BookService = RetrofitInitializer().bookService
//
//        suspend fun getAllBooks():List<BookDto>?{
//            return try {
//                val response = bookService.getAll()
//                response.let { response
//                }
//
//
//            } catch (e: Exception) {
//                Log.e(TAG,"getAllBooks",e)
//                null
//            }
//        }
//}
