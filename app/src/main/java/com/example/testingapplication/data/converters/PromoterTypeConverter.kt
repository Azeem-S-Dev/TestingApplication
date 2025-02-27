package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Promoter
import com.google.gson.Gson

class PromoterTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Promoter? {
        return value?.let {
            Gson().fromJson(value, Promoter::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Promoter?): String {
        return Gson().toJson(value)
    }
}