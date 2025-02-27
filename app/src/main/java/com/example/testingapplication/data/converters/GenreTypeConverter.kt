package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Genre
import com.google.gson.Gson

class GenreTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Genre? {
        return value?.let {
            Gson().fromJson(value, Genre::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Genre?): String {
        return Gson().toJson(value)
    }
}