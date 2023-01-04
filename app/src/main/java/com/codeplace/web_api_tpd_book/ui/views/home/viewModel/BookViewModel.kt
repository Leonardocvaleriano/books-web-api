package com.codeplace.web_api_tpd_book.ui.views.home.viewModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeplace.web_api_tpd_book.repository.BookRepository
import com.codeplace.web_api_tpd_book.ui.views.home.base.BaseViewModel
import com.codeplace.web_api_tpd_book.webapi.models.BookDto

// This class keep all the data information, even if the activity has been destroyed for any life cycle.

class BookViewModel(private val bookRepository: BookRepository): BaseViewModel() {

    val bookList = MutableLiveData<List<BookDto>>()
    fun getBookList() = fetchData(bookList) {
        bookRepository.getBooksList()
    }

//      Cod o qual mostra o momento o qual a viewModel e criada e destruida
//     (Rotacao da tela)

//    init {
//        Log.i("viewModel","criando viewModel")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.i("viewModel","destruindo viewModel")
//    }
}

