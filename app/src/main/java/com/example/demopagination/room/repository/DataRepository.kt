package com.example.demopagination.room.dao;

import androidx.lifecycle.LiveData
import com.example.demopagination.model.Items





class DataRepository(private val dataDao: DataDao) {
    val items: LiveData<List<Items>> = dataDao.getItems()



    suspend fun insertItems(items: List<Items>) {
        dataDao.clearItems()
        dataDao.insertItems(items)
    }
}



