package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.ImageX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageXListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<ImageX>? {
        val type = object : TypeToken<List<ImageX>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<ImageX>?): String {
        return Gson().toJson(list)
    }
}