package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Links
import com.google.gson.Gson

class LinksTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Links? {
        return value?.let {
            Gson().fromJson(value, Links::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Links?): String {
        return Gson().toJson(value)
    }
}