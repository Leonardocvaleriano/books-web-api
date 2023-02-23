package com.codeplace.bookswebapi.ui.views.bookDetails.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.bookswebapi.repository.BookDetailsRepository
import com.codeplace.bookswebapi.ui.views.bookDetails.base.BaseViewModelDetail
import com.codeplace.bookswebapi.ui.views.bookDetails.models.BookDetailslDto

class BookDetailsViewModel(private val bookDetailsRepository: BookDetailsRepository):BaseViewModelDetail(){
    var id:Int = 0
    val detailBookItens = MutableLiveData<BookDetailslDto>()

      fun getBook() = fetchData(detailBookItens) {
          bookDetailsRepository.getDetailsBook(id)
    }
}