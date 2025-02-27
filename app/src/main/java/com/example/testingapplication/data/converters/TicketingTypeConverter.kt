package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Ticketing
import com.google.gson.Gson

class TicketingTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Ticketing? {
        return value?.let {
            Gson().fromJson(value, Ticketing::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Ticketing?): String {
        return Gson().toJson(value)
    }
}