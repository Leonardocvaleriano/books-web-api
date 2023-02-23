package com.codeplace.bookswebapi.ui.views.home.view.activity
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeplace.bookswebapi.databinding.ActivityMainBinding
import com.codeplace.bookswebapi.repository.BooksRepository
import com.codeplace.bookswebapi.ui.views.bookDetails.view.activity.DetailsActivity
import com.codeplace.bookswebapi.ui.views.home.view.adapter.BooksListAdapter
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModel
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModelFactory
import com.codeplace.bookswebapi.ui.views.home.models.BookDto

class HomeActivity : AppCompatActivity() {

    private lateinit var booksListAdapter: BooksListAdapter
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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
        initValues()
        initObservables()
     }

    private fun initValues(){
        binding.progressBar.visibility = ProgressBar.VISIBLE
        viewModel.getBookList()
    }

    private fun initObservables(){
           viewModel.bookList.observe(this){bookList ->
               initRecyclerAdapter(bookList)
           }
    }
//
//    fun initBookList(result:JSONObject) {
//        //mockSchedules(result)
//        initRecyclerAdapter()
//    }

    private fun initRecyclerAdapter(bookList:List<BookDto>){
      with(binding){
          recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
          booksListAdapter = BooksListAdapter(bookList)
          recyclerView.adapter = booksListAdapter
      }
        initDetailActivity()
        binding.progressBar.visibility = ProgressBar.GONE
    }

    private fun initDetailActivity(){
        booksListAdapter.onItemClick = { book ->
            val id = book.id
            Intent(this, DetailsActivity::class.java).also {
                it.putExtra("EXTRA_ID", id)
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





