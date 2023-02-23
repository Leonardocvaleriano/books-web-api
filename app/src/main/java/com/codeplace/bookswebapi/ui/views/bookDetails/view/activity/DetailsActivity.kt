package com.codeplace.bookswebapi.ui.views.bookDetails.view.activity

import android.opengl.Visibility
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.databinding.ActivityDetailsBinding
import com.codeplace.bookswebapi.repository.BookDetailsRepository
import com.codeplace.bookswebapi.ui.views.bookDetails.models.BookDetailslDto
import com.codeplace.bookswebapi.ui.views.bookDetails.viewModel.BookDetailsViewModelFactory
import com.codeplace.bookswebapi.ui.views.bookDetails.viewModel.BookDetailsViewModel


class DetailsActivity: AppCompatActivity() {
    private val binding by lazy {
         ActivityDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        // we need to create this provider, because this viewModel has a dependency with the android framework which is the repository.
        // dependency describes the relationship among activities and specifies the particular order which they need to be performed.
        val repository = BookDetailsRepository()
        val factory = BookDetailsViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider.get(BookDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


       val id = intent.getIntExtra("EXTRA_ID",0)
        if (id != null) {
            initValues(id)
        }
        initObservable()
    }



    //Call the repository to bring this id information
    private fun initValues(id:Int){
        viewModel.id = id
        viewModel.getBook()
        binding.progressBar.visibility = ProgressBar.VISIBLE
     }

   private fun initObservable(){
        viewModel.detailBookItens.observe(this){ bookDetailItems ->
            initBinding(bookDetailItems)
            }
   }

    private fun initBinding(bookDetailItems:BookDetailslDto) {
        with(binding) {
            txtTitle.text = bookDetailItems.title
            txtAuthor.text = bookDetailItems.author
            txtCurrency.text = bookDetailItems.currencyCode
            txtPrice.text = bookDetailItems.price.toString()
            txtIsbn.text = bookDetailItems.isbn
            txtDescription.text = bookDetailItems.description
         }
        binding.progressBar.visibility = ProgressBar.GONE
    }
 }