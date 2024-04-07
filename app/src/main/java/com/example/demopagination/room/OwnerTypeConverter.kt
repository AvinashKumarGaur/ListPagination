package com.example.demopagination.room

import androidx.room.TypeConverter
import com.example.demopagination.model.Owner

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OwnerTypeConverter {
    @TypeConverter
    fun fromString(value: String): Owner {
        // Convert a JSON string to an ArrayList of Branch objects
        val type = object : TypeToken<Owner>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toString(owner: Owner): String {
        // Convert an ArrayList of Branch objects to a JSON string
        return Gson().toJson(owner)
    }
}