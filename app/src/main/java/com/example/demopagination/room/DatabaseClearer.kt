package com.kent.service.engg.roomNew

import android.util.Log

class DatabaseClearer(private val database: AppDatabase) {

    fun clearAllData() {
        database.runInTransaction {
            Log.e("checkDataClear","true")
            database.clearAllTables()
        }
    }
}