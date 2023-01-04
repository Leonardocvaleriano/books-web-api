package com.codeplace.web_api_tpd_book.ui.views.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeplace.justconsumeapiwithcall.databinding.ActivityMainBinding
import com.codeplace.web_api_tpd_book.repository.BookRepository
import com.codeplace.web_api_tpd_book.ui.views.home.viewModel.BookViewModel
import com.codeplace.web_api_tpd_book.ui.views.home.viewModel.BookViewModelFactory


class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private val viewModel by lazy {
        // we need to create this provider, because this viewModel has a dependency with the android framework which is the repository.
        // dependency describes the relationship among activities and specifies the particular order which they need to be performed.
        val repository = BookRepository()
        val factory = BookViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
         provider.get(BookViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initValues()
        initObservables()
      }


    fun initValues(){
         viewModel.getBookList()
    }

    // Implement the recyclerView
    fun initObservables(){
        viewModel.bookList.observe(this, Observer {
            it.map {
                binding.txtHelloWord.setText(it.title)
            }
        })
    }


}





