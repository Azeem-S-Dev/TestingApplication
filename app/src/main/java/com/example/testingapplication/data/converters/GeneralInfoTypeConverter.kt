package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.GeneralInfo
import com.google.gson.Gson

class GeneralInfoTypeConverter {
    @TypeConverter
    fun fromString(value: String?): GeneralInfo? {
        return value?.let {
            Gson().fromJson(value, GeneralInfo::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: GeneralInfo?): String {
        return Gson().toJson(value)
    }
}