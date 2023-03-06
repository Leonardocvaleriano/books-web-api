package com.codeplace.bookswebapi.ui.views.home.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.bookswebapi.ui.views.home.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.models.BookDto
import kotlinx.coroutines.launch
import retrofit2.Response

// I need to call the api here.

private const val TAG = "BookWebClientApi"

open class BaseViewModel:ViewModel() {
     fun fetchDataRv(liveData: MutableLiveData<List<BookDto>>, service: suspend ()-> Response<List<BookDto>?>){
          viewModelScope.launch{
               try {
                    val response = service()
                    if (response.isSuccessful){
                        val jsonResponse = response.body()
                         liveData.value = jsonResponse
                     }
               } catch (e: Exception) {
                    Log.e(TAG, "fetchDataHome", e)
               }
          }
     }

    fun fetchDataBookDetail(liveData: MutableLiveData<BookDetailslDto>, service: suspend ()-> Response<BookDetailslDto>){
        viewModelScope.launch{
            try {
                val response = service()
                if (response.isSuccessful){
                    val jsonResponse = response.body()
                    liveData.value = jsonResponse

                    }
            } catch (e: Exception) {
                Log.e(TAG, "fetchDataBookDetail", e)
            }
        }
    }


}
