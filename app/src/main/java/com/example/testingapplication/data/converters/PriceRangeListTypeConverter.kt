package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.PriceRange
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PriceRangeListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<PriceRange>? {
        val type = object : TypeToken<List<PriceRange>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<PriceRange>?): String {
        return Gson().toJson(list)
    }
}