package com.codeplace.bookswebapi.ui.views.detail.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.bookswebapi.databinding.ActivityDetailsBinding


class DetailsActivity: AppCompatActivity() {
    private val binding by lazy {
         ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initValues()

        }

    fun initValues(){
        val title = intent.getStringExtra("EXTRA_TITLE")
        val author = intent.getStringExtra("EXTRA_AUTHOR")
        val price = intent.getStringExtra("EXTRA_PRICE")
        val currency = intent.getStringExtra("EXTRA_CURRENCY")
        val isbn = intent.getStringExtra("EXTRA_ISBN")


            with(binding){
                txtTitle.text = title
                txtAuthor.text = author
                txtPrice.text = price
                txtCurrency.text = currency
                txtIsbn.text = isbn
            }

    }
}