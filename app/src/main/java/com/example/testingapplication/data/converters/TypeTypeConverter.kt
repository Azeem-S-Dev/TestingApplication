package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Type
import com.google.gson.Gson

class TypeTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Type? {
        return value?.let {
            Gson().fromJson(value, Type::class.java)
        }
    }

    @TypeConverter
    fun fromType(value: Type?): String {
        return Gson().toJson(value)
    }
}