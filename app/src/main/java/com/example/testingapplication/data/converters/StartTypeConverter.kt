package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Start
import com.google.gson.Gson

class StartTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Start? {
        return value?.let {
            Gson().fromJson(value, Start::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Start?): String {
        return Gson().toJson(value)
    }
}