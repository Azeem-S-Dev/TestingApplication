package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.UrlList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UrlListListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<UrlList>? {
        val type = object : TypeToken<List<UrlList>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<UrlList>?): String {
        return Gson().toJson(list)
    }
}