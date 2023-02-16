package com.codeplace.bookswebapi.ui.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.bookswebapi.databinding.ItemBooksBinding
import com.codeplace.bookswebapi.webapi.models.BookDto

class BooksListAdapter(
    var booksList: List<BookDto>):RecyclerView.Adapter<BooksListAdapter.BookListViewHolder>() {

    inner class BookListViewHolder(val binding: ItemBooksBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(ItemBooksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.binding.apply {
            val booksList = booksList[position]
            txtTitle.text = booksList.title
            txtAuthor.text = booksList.author
            txtCurrency.text = booksList.currencyCode
            txtPrice.text = booksList.price.toString()
            txtIsbn.text = booksList.isbn


        }

    }

    override fun getItemCount() = booksList.size


}
