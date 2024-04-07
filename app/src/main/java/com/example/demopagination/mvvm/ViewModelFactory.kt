package com.example.demopagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demopagination.room.dao.DataRepository


class ViewModelFactory(
    private val repository: GithubRepository,
    private val userRepository: DataRepository,
     private val mainActivity: MainActivity
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            return GithubViewModel(repository,userRepository,mainActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
