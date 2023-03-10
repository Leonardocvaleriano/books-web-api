package com.codeplace.bookswebapi.ui.views.home.viewModel
import androidx.lifecycle.MutableLiveData
import com.codeplace.bookswebapi.repository.BooksRepository
import com.codeplace.bookswebapi.stateFlow.StateFlow
import com.codeplace.bookswebapi.ui.views.home.base.BaseViewModel
import com.codeplace.bookswebapi.ui.views.home.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.models.BookDto

// This class keep all the data information, even if the activity has been destroyed for any life cycle.

class BooksViewModel(private val booksRepository: BooksRepository): BaseViewModel() {


    val bookList = MutableLiveData<StateFlow>()
    val bookDetailItems = MutableLiveData<StateFlow>()
    var id: Int = 0

    fun getBookList() = fetchDataRv(bookList) {
        booksRepository.getBooksList()
    }

    fun getBookDetail() = fetchDataBookDetail(bookDetailItems) {
        booksRepository.getDetailsBook(id)
    }

//    fun fillBookList(result: JSONObject){
//       val resultJSONArray =  result.getJSONArray("results")
//
//    }

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

