package com.example.demopagination.room

import androidx.room.TypeConverter
import com.example.demopagination.model.License

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LicenseTypeConverter {
    @TypeConverter
    fun fromString(value: String): License {
        // Convert a JSON string to an ArrayList of Branch objects
        val type = object : TypeToken<License>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toString(license: License): String {
        // Convert an ArrayList of Branch objects to a JSON string
        return Gson().toJson(license)
    }
}