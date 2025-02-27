package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.ClassificationX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ClassificationXListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<ClassificationX>? {
        val type = object : TypeToken<List<ClassificationX>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<ClassificationX>?): String {
        return Gson().toJson(list)
    }
}