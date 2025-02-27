package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.BoxOfficeInfo
import com.google.gson.Gson

class BoxOfficeInfoTypeConverter {
    @TypeConverter
    fun fromString(value: String?): BoxOfficeInfo? {
        return value?.let {
            Gson().fromJson(value, BoxOfficeInfo::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: BoxOfficeInfo?): String {
        return Gson().toJson(value)
    }
}