package com.codeplace.bookswebapi.ui.views.home
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeplace.bookswebapi.databinding.ActivityDetailsBinding
import com.codeplace.bookswebapi.repository.BookRepository
import com.codeplace.bookswebapi.ui.views.home.viewModel.BookViewModel
import com.codeplace.bookswebapi.ui.views.home.viewModel.BookViewModelFactory
import com.codeplace.bookswebapi.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        //ActivityMainBinding.inflate(layoutInflater)
        ActivityDetailsBinding.inflate(layoutInflater)
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
        //initObservables()



     }

    fun initValues(){
         viewModel.getBookList()
    }

//    fun initObservables(){
//        viewModel.bookList.observe(this, Observer {
//           it.map {
//               binding.txtTitle.setText(it.title)
//               binding.txtAuthor.setText(it.author)
//               binding.txtCurrency.setText(it.currencyCode)
//               binding.txtPrice.setText(it.price.toString())
//               binding.txtIsbn.setText(it.isbn)
//           }
//        })
//    }


//     What do observables does?
//     Implement the recyclerView
//    fun initObservables(){
//        viewModel.bookList.observe(this, Observer {
//           initBooks(it as JSONObject)
//        })
//    }


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

}





