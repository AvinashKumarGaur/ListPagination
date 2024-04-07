package com.example.demopagination.view

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
import com.example.demopagination.OnRepositoryClickListener
import com.example.demopagination.R
import com.example.demopagination.adapter.RepositoryAdapter
import com.example.demopagination.databinding.ActivityMainBinding
import com.example.demopagination.mvvm.ViewModelFactory
import com.example.demopagination.model.Items
import com.example.demopagination.mvvm.MainActivityViewModel
import com.example.demopagination.remot.GithubRepository
import com.example.demopagination.room.AppDatabase
import com.example.demopagination.room.dao.DataRepository


class MainActivity : AppCompatActivity(), OnRepositoryClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoryAdapter
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)

     //   recyclerView = findViewById(R.id.recyclerView)
       // val editTextSearch = findViewById<EditText>(R.id.inputSearch)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(emptyList(),this)
        binding.recyclerView.adapter = adapter
        // Initialize Room Database and DataDao
        val db = AppDatabase.getInstance(applicationContext)
        val dataDao = db.dataDao()


        // Create DataRepository instance
        val dataRepository = DataRepository(dataDao)
        val repository = GithubRepository() // Provide an instance of your repository here
        val viewModelFactory = ViewModelFactory(repository,dataRepository,this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
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






        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter your data based on the search query
                if (s != null) {
                    if (s.length>=1) {
                        val searchText = s.toString().trim()
                        viewModel.searchRepositories(searchText, 10)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })


    }

    override fun onRepositoryClick(repository: Items) {
        val intent = Intent(this, DetailsActivityCompose::class.java)
        intent.putExtra("repository", repository)
        startActivity(intent)
    }
}

