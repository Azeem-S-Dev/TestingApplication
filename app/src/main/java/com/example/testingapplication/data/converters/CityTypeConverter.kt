package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.City
import com.google.gson.Gson

class CityTypeConverter {
    @TypeConverter
    fun fromString(value: String?): City? {
        return value?.let {
            Gson().fromJson(value, City::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: City?): String {
        return Gson().toJson(value)
    }
}