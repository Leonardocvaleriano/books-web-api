package com.codeplace.bookswebapi.ui.views.home.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.bookswebapi.webapi.models.BookDto
import kotlinx.coroutines.launch
import retrofit2.Response
// I need to call the api here.

private const val TAG = "BookWebClientApi"
open class BaseViewModel:ViewModel() {
     fun fetchData(liveData: MutableLiveData<List<BookDto>>, service: suspend ()-> Response<List<BookDto>?>){
          viewModelScope.launch {
               try {
                    val response = service()
                    if (response.isSuccessful){
                        val jsonResponse = response.body()
                           liveData.value = jsonResponse
                    }
               } catch (e: Exception) {
                    Log.e(TAG, "fetchData", e)
               }
          }

     }

}
