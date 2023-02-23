package com.codeplace.bookswebapi.ui.views.bookDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.repository.BookDetailsRepository


class BookDetailsViewModelFactory(private val repository: BookDetailsRepository):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookDetailsViewModel(repository) as T
    }

}