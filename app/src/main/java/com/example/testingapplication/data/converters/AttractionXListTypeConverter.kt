package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.AttractionX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AttractionXListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<AttractionX>? {
        val type = object : TypeToken<List<AttractionX>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<AttractionX>?): String {
        return Gson().toJson(list)
    }
}