package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Event
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Event>? {
        val type = object : TypeToken<List<Event>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Event>?): String {
        return Gson().toJson(list)
    }
}