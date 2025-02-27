package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Product>? {
        val type = object : TypeToken<List<Product>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Product>?): String {
        return Gson().toJson(list)
    }
}