package com.codeplace.bookswebapi.ui.views.home.view.activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeplace.bookswebapi.databinding.ActivityMainBinding
import com.codeplace.bookswebapi.repository.BooksRepository
import com.codeplace.bookswebapi.stateFlow.StateFlow
import com.codeplace.bookswebapi.ui.views.home.models.BookDto
import com.codeplace.bookswebapi.ui.views.home.view.adapter.BooksListAdapter
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModel
import com.codeplace.bookswebapi.ui.views.home.viewModel.BooksViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var booksListAdapter: BooksListAdapter
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
     }

    private val viewModel by lazy {
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
           viewModel.bookList.observe(this){
               when(it){
                   is StateFlow.Loading -> loading(it.loading)
                   is StateFlow.Success<*>-> initRecyclerAdapter(it.data as List<BookDto>)
                   is StateFlow.Error -> showError(it.errorMessage)
               }
           }
    }
    private fun loading(loading: Boolean){
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showError(error:String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

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
            Intent(this, BookDetailsActivity::class.java).also {
                it.putExtra("EXTRA_ID", id)
                startActivityForResult(it, REQUEST_CODE)
             }
        }
    }

    companion object{
        const val REQUEST_CODE = 1
        const val RESULT_CODE = 2
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE){
            if (data!!.hasExtra("EXTRA_ERROR")){
              val error = data.getStringExtra("EXTRA_ERROR")
                Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
                }
        }
    }
}



