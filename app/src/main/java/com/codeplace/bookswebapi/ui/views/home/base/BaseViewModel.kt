package com.codeplace.bookswebapi.ui.views.home.base

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.bookswebapi.stateFlow.StateFlow
import com.codeplace.bookswebapi.ui.views.home.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.models.BookDto
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "BookWebClientApi"

open class BaseViewModel:ViewModel() {
     fun fetchDataRv(liveData: MutableLiveData<StateFlow>, service: suspend ()-> Response<List<BookDto>?>){
          viewModelScope.launch{
               try {
                   val response = service()
                   if (response.isSuccessful){
                        val jsonResponse = response.body()
                         liveData.value = StateFlow.Success(jsonResponse)
                     }
               } catch (e: Exception) {
                    Log.e(TAG, "fetchDataHome", e)
               }
          }
     }

    @SuppressLint("SuspiciousIndentation")
    fun fetchDataBookDetail(liveData: MutableLiveData<StateFlow>, service: suspend ()-> Response<BookDetailslDto>){
        viewModelScope.launch{
            liveData.value = StateFlow.Loading(true)
                try {
                val response = service()
                    liveData.value = StateFlow.Loading(false)
                    if (response.isSuccessful){
                        val jsonResponse = response.body()
                    liveData.value = StateFlow.Success(jsonResponse)
                    }
                    else if (response.code() == 500){
                        liveData.value = StateFlow.Error("Internal Server Error","500", "The Server has encountered a situation it does not know how to handle","undefined")
                    }
            } catch (e: Exception) {
                Log.e(TAG, "fetchDataBookDetail", e)
                    liveData.value = StateFlow.Error("Unknown Error ","Undefined", "Undefined","undefined")

            }
        }
    }


}
