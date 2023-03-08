package com.codeplace.bookswebapi.ui.views.home.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.databinding.ActivityBookDetailsBinding
import com.codeplace.bookswebapi.repository.BooksRepository
import com.codeplace.bookswebapi.stateFlow.StateFlow
import com.codeplace.bookswebapi.ui.views.home.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModel
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModelFactory


class BookDetailsActivity: AppCompatActivity() {
    private val binding by lazy {
         ActivityBookDetailsBinding.inflate(layoutInflater)
    }


    private val viewModel by lazy {
        // we need to create this provider, because this viewModel has a dependency with the android framework which is the repository.
        // dependency describes the relationship among activities and specifies the particular order which they need to be performed.
        val repository = BooksRepository()
        val factory = BooksViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider.get(BooksViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getIntExtra("EXTRA_ID",0)
         initValues(id)
         initObservable()
    }

    //Call the repository to bring this id information
    private fun initValues(id:Int){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.id = id
        viewModel.getBookDetail()
     }

   private fun initObservable(){
        viewModel.bookDetailItems.observe(this){
                when(it){
                    is StateFlow.Loading-> loading(it.loading)
                    is StateFlow.Success<*>-> initBookDetail(it.data as BookDetailslDto)
                    is StateFlow.Error-> showError(it.errorMessage)
                }
        }
   }

    private fun initBookDetail(result: BookDetailslDto) {
       // mockSchedules(result)
        with(binding){
            txtTitle.text = result.title
            txtAuthor.text = result.author
            txtCurrency.text = result.currencyCode
            txtPrice.text = result.price.toString()
            txtIsbn.text = result.isbn
            txtDescription.text = result.description
        }
        binding.view.visibility = View.VISIBLE
        binding.imgBook.visibility = View.VISIBLE
        binding.txtDescriptionTitle.visibility = View.VISIBLE
    }

    private fun loading(loading: Boolean){
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE

    }

    private fun showError(error:String){
        Intent().apply {
            putExtra("EXTRA_ERROR", error)
            setResult(2, this)
            finish()
        }
    }
}