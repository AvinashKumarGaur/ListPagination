package com.example.demopagination.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demopagination.remot.GithubRepository
import com.example.demopagination.room.dao.DataRepository
import com.example.demopagination.view.MainActivity


class ViewModelFactory(
    private val repository: GithubRepository,
    private val userRepository: DataRepository,
    private val mainActivity: MainActivity
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository,userRepository,mainActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
