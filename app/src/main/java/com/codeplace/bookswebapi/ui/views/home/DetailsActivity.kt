package com.codeplace.bookswebapi.ui.views.home

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

        with(binding){
            txtTitle.text = "teste"

        }
    }
}