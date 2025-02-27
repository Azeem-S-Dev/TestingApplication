package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Promoter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PromoterListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Promoter>? {
        val type = object : TypeToken<List<Promoter>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Promoter>?): String {
        return Gson().toJson(list)
    }
}