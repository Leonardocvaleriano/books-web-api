package com.codeplace.bookswebapi.ui.views.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.repository.DetailBookRepository


class DetailBookViewModelFactory(private val repository: DetailBookRepository):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }

}