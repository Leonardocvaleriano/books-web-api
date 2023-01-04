package com.codeplace.web_api_tpd_book.ui.views.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeplace.web_api_tpd_book.repository.BookRepository

class BookViewModelFactory(
    private val repository:BookRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repository) as T
    }
}