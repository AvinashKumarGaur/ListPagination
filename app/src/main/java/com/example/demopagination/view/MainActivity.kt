package com.example.demopagination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demopagination.room.AppDatabase
import com.example.demopagination.room.dao.DataRepository


class MainActivity : AppCompatActivity(), OnRepositoryClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoryAdapter
    private lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val editTextSearch = findViewById<EditText>(R.id.inputSearch)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(emptyList(),this)
        recyclerView.adapter = adapter
        // Initialize Room Database and DataDao
        val db = AppDatabase.getInstance(applicationContext)
        val dataDao = db.dataDao()


        // Create DataRepository instance
        val dataRepository = DataRepository(dataDao)
        val repository = GithubRepository() // Provide an instance of your repository here
        val viewModelFactory = ViewModelFactory(repository,dataRepository,this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GithubViewModel::class.java)
if (viewModel.isNetworkConnected(this)){
    viewModel.repositories.observe(this) { repositories ->
        adapter.repositories = repositories
        adapter.notifyDataSetChanged()
    }
}
        else{
    viewModel.items.observe(this) { items ->
        items?.let {
            adapter.repositories = it
            adapter.notifyDataSetChanged()
            Log.d("data", it.toString())
        }
    }
        }






        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter your data based on the search query
                val searchText = s.toString().trim()
                viewModel.searchRepositories(searchText, 10)
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })


    }

    override fun onRepositoryClick(repository: Items) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("repository", repository)
        startActivity(intent)
    }
}

