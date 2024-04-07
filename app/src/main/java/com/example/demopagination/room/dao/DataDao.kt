package com.example.demopagination.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demopagination.model.Items


@Dao
interface DataDao {

    @Query("SELECT * FROM Items")
    fun getItems(): LiveData<List<Items>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(user: List<Items>)

    @Query("DELETE FROM Items")
    suspend fun clearItems()

}