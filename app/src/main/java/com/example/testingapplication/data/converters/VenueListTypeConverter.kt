package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Venue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VenueListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Venue>? {
        val type = object : TypeToken<List<Venue>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Venue>?): String {
        return Gson().toJson(list)
    }
}