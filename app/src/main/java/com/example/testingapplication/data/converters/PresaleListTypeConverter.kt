package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Presale
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PresaleListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Presale>? {
        val type = object : TypeToken<List<Presale>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Presale>?): String {
        return Gson().toJson(list)
    }
}