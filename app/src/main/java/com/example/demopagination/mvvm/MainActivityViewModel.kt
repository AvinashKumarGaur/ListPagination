package com.example.demopagination.mvvm

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demopagination.model.Items
import com.example.demopagination.remot.GithubRepository
import com.example.demopagination.room.dao.DataRepository

import kotlinx.coroutines.launch

// GithubViewModel.kt
class MainActivityViewModel(private val repository: GithubRepository, private val userRepository: DataRepository, private val context: Context ) : ViewModel() {
    private val _repositories = MutableLiveData<List<Items>>().apply { value = emptyList() }
    val progressVM = ProgressVM()
    val repositories: LiveData<List<Items>> = _repositories
    val items: LiveData<List<Items>> = userRepository.items
    private val aggregatedItems = mutableListOf<Items>()
    val isConnected = isNetworkConnected(context)


    fun searchRepositories(query: String, page: Int) {

       // progressVM.visibility.set(View.VISIBLE)
        viewModelScope.launch {
            progressVM.visibility.set(View.GONE)
             if (isConnected) {
                 val response = repository.searchRepositories(query, page)
                 _repositories.value = response
                 handleServerResponse(response)
            } else {
                // Fetch data from local Room database

                // val itemsDao: LiveData<List<Items>> = userRepository.items
            //_repositories.value=userRepository.items as List<Items>
            }

        }
    }



    private fun handleServerResponse(response: List<Items>) {
        // Append the received items to the aggregated list
        aggregatedItems.addAll(response)

        // If the aggregated list size exceeds 15, trim it to 15 items
        if (aggregatedItems.size > 15) {
            val itemsToInsert = aggregatedItems.take(15)
            insertUser(itemsToInsert)
            // Clear the aggregated list except the last 5 items
            aggregatedItems.clear()
            aggregatedItems.addAll(response.takeLast(5))
        } else {
            // Update UI with the aggregated items
            _repositories.value = aggregatedItems.toList()
        }
    }

    fun insertUser(response: List<Items>) {
        viewModelScope.launch {
            userRepository.insertItems(response)
        }
    }

     fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}
