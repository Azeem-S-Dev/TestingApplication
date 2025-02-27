package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Event
import com.google.gson.Gson

class EventTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Event? {
        return value?.let {
            Gson().fromJson(value, Event::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Event?): String {
        return Gson().toJson(value)
    }
}