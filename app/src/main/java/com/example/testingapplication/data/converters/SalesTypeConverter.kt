package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Sales
import com.google.gson.Gson

class SalesTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Sales? {
        return value?.let {
            Gson().fromJson(value, Sales::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Sales?): String {
        return Gson().toJson(value)
    }
}