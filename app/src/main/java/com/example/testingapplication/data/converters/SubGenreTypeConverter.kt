package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.SubGenre
import com.google.gson.Gson

class SubGenreTypeConverter {
    @TypeConverter
    fun fromString(value: String?): SubGenre? {
        return value?.let {
            Gson().fromJson(value, SubGenre::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: SubGenre?): String {
        return Gson().toJson(value)
    }
}