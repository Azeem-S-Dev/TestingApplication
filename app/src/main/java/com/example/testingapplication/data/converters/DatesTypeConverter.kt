package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Dates
import com.google.gson.Gson

class DatesTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Dates? {
        return value?.let {
            Gson().fromJson(value, Dates::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Dates?): String {
        return Gson().toJson(value)
    }
}