package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Location
import com.google.gson.Gson

class LocationTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Location? {
        return value?.let {
            Gson().fromJson(value, Location::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Location?): String {
        return Gson().toJson(value)
    }
}