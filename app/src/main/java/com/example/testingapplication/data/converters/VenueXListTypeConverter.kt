package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.VenueX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VenueXListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<VenueX>? {
        val type = object : TypeToken<List<VenueX>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<VenueX>?): String {
        return Gson().toJson(list)
    }
}