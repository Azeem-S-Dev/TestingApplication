package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.SubType
import com.google.gson.Gson

class SubTypeTypeConverter {
    @TypeConverter
    fun fromString(value: String?): SubType? {
        return value?.let {
            Gson().fromJson(value, SubType::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: SubType?): String {
        return Gson().toJson(value)
    }
}