package com.codeplace.bookswebapi.ui.views.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.bookswebapi.databinding.ItemBooksBinding
import com.codeplace.bookswebapi.ui.views.home.models.BookDto

class BooksListAdapter(
    var booksList: List<BookDto>,
 ) : RecyclerView.Adapter<BooksListAdapter.BookListViewHolder>() {
        var onItemClick: ((BookDto) -> Unit)? = null
        inner class BookListViewHolder(val binding: ItemBooksBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(
            ItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.binding.apply {
            val booksList = booksList[position]
            txtTitle.text = booksList.title
            txtAuthor.text = booksList.author
            txtCurrency.text = booksList.currencyCode
            txtPrice.text = booksList.price.toString()
            txtIsbn.text = booksList.isbn
            cardBook.setOnClickListener {
                onItemClick?.invoke(booksList)
            }

        }

    }

    override fun getItemCount() = booksList.size


}
