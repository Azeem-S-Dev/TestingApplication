package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Dma
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DmaListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Dma>? {
        val type = object : TypeToken<List<Dma>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Dma>?): String {
        return Gson().toJson(list)
    }
}