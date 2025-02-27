package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Attraction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AttractionListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Attraction>? {
        val type = object : TypeToken<List<Attraction>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Attraction>?): String {
        return Gson().toJson(list)
    }
}