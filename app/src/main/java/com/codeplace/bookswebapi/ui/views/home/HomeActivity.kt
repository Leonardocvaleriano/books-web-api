package com.codeplace.bookswebapi.ui.views.home
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeplace.bookswebapi.databinding.ActivityMainBinding
import com.codeplace.bookswebapi.repository.BookRepository
import com.codeplace.bookswebapi.ui.views.detail.view.activity.DetailsActivity
import com.codeplace.bookswebapi.ui.views.home.adapter.BooksListAdapter
import com.codeplace.bookswebapi.ui.views.home.viewModel.BookViewModel
import com.codeplace.bookswebapi.ui.views.home.viewModel.BookViewModelFactory
import com.codeplace.bookswebapi.webapi.models.BookDto

class HomeActivity : AppCompatActivity() {

    private lateinit var booksListAdapter: BooksListAdapter
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

    private fun initValues(){
        binding.progressBar.visibility = ProgressBar.VISIBLE
        viewModel.getBookList()
    }

    private fun initObservables(){
           viewModel.bookList.observe(this){
               initRecyclerAdapter(it)
           }
    }
//
//    fun initBookList(result:JSONObject) {
//        //mockSchedules(result)
//        initRecyclerAdapter()
//    }

    private fun initRecyclerAdapter(result:List<BookDto>){
      with(binding){
          recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
          booksListAdapter = BooksListAdapter(result)
          recyclerView.adapter = booksListAdapter
      }
        initDetailActivity()
        binding.progressBar.visibility = ProgressBar.GONE
    }

    private fun initDetailActivity(){
        booksListAdapter.onItemClick = { book ->
            val id = book.id
            val title = book.title
            val author = book.author
            val currencyCode = book.currencyCode
            val price = book.price.toString()
            val isbn = book.isbn.toString()

            Intent(this, DetailsActivity::class.java).also {
                it.putExtra("EXTRA_ID", id)
                it.putExtra("EXTRA_TITLE", title)
                it.putExtra("EXTRA_AUTHOR", author)
                it.putExtra("EXTRA_CURRENCY", currencyCode)
                it.putExtra("EXTRA_ISBN", isbn)
                it.putExtra("EXTRA_PRICE", price)
            startActivity(it)
            }
        }
    }
}
//  What does a mock do
//    private fun initBooks(result:JSONObject ) {
//        mockBooks(result)
//        //initRecyclerAdapter()
//    }

//
//    private fun mockBooks(result: JSONObject) {
//        viewModel.fillBookList(result)
//    }
//





