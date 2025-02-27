package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.AgeRestrictions
import com.google.gson.Gson

class AgeRestrictionsTypeConverter {
    @TypeConverter
    fun fromString(value: String?): AgeRestrictions? {
        return value?.let {
            Gson().fromJson(value, AgeRestrictions::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: AgeRestrictions?): String {
        return Gson().toJson(value)
    }
}