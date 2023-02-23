package com.codeplace.bookswebapi.ui.views.details.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.bookswebapi.ui.views.details.models.DetailBookDto
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "BookWebClientApi"
open class BaseViewModelDetail:ViewModel() {

    fun fetchData(liveData: MutableLiveData<DetailBookDto>, service: suspend ()-> Response<DetailBookDto>){
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