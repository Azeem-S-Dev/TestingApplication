package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Public
import com.google.gson.Gson

class PublicTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Public? {
        return value?.let {
            Gson().fromJson(value, Public::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Public?): String {
        return Gson().toJson(value)
    }
}