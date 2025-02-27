package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Country
import com.google.gson.Gson

class CountryTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Country? {
        return value?.let {
            Gson().fromJson(value, Country::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Country?): String {
        return Gson().toJson(value)
    }
}