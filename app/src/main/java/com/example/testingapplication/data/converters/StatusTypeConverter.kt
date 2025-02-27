package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Status
import com.google.gson.Gson

class StatusTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Status? {
        return value?.let {
            Gson().fromJson(value, Status::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Status?): String {
        return Gson().toJson(value)
    }
}