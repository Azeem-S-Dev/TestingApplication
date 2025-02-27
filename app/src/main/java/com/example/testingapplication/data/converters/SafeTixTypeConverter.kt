package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.SafeTix
import com.google.gson.Gson

class SafeTixTypeConverter {
    @TypeConverter
    fun fromString(value: String?): SafeTix? {
        return value?.let {
            Gson().fromJson(value, SafeTix::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: SafeTix?): String {
        return Gson().toJson(value)
    }
}