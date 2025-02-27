package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.UpcomingEvents
import com.google.gson.Gson

class UpcomingEventsTypeConverter {
    @TypeConverter
    fun fromString(value: String?): UpcomingEvents? {
        return value?.let {
            Gson().fromJson(value, UpcomingEvents::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: UpcomingEvents?): String {
        return Gson().toJson(value)
    }
}