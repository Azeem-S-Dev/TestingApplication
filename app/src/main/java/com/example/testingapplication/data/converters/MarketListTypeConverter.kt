package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Market
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MarketListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Market>? {
        val type = object : TypeToken<List<Market>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Market>?): String {
        return Gson().toJson(list)
    }
}