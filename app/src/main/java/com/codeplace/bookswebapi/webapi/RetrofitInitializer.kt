package com.codeplace.bookswebapi.webapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    // Create Logger

    val logger:HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client:OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()


    //Create retrofit instance
    val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl("http://tpbookserver.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    //Initializing the retrofit as our bookService through the BookService Interface implemented.
    val bookService = retrofit.create(BookService::class.java)
}

