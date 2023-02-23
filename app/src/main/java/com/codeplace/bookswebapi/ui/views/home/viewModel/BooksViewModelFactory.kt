package com.codeplace.bookswebapi.ui.views.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.repository.BooksRepository

class BooksViewModelFactory(
    private val repository:BooksRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksViewModel(repository) as T
    }
}