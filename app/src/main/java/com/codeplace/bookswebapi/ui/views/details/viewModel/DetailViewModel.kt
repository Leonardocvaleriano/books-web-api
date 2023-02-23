package com.codeplace.bookswebapi.ui.views.details.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.bookswebapi.repository.DetailBookRepository
import com.codeplace.bookswebapi.ui.views.details.base.BaseViewModelDetail
import com.codeplace.bookswebapi.ui.views.details.models.DetailBookDto

class DetailViewModel(private val detailBookRepository: DetailBookRepository):BaseViewModelDetail(){
    var id:Int = 0
    val detailBookItens = MutableLiveData<DetailBookDto>()

      fun getBook() = fetchData(detailBookItens) {
          detailBookRepository.getDetailsBook(id)
    }
}