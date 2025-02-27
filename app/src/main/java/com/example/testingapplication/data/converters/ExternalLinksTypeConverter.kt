package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.ExternalLinks
import com.google.gson.Gson

class ExternalLinksTypeConverter {
    @TypeConverter
    fun fromString(value: String?): ExternalLinks? {
        return value?.let {
            Gson().fromJson(value, ExternalLinks::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: ExternalLinks?): String {
        return Gson().toJson(value)
    }
}