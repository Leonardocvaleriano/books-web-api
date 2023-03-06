package com.codeplace.bookswebapi.ui.views.home.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.databinding.ActivityBookDetailsBinding
import com.codeplace.bookswebapi.repository.BooksRepository
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
        viewModel.bookDetailItems.observe(this){ bookDetailItems ->

            with(binding){
                txtTitle.text = bookDetailItems.title
                txtAuthor.text = bookDetailItems.author
                txtCurrency.text =  bookDetailItems.currencyCode
                txtPrice.text = bookDetailItems.price.toString()
                txtIsbn.text = bookDetailItems.isbn
                txtDescription.text = bookDetailItems.description
            }
            binding.progressBar.visibility = View.GONE
            binding.view.visibility = View.VISIBLE
            binding.txtDescriptionTitle.visibility = View.VISIBLE
            binding.imgBook.visibility = View.VISIBLE
        }

   }

}