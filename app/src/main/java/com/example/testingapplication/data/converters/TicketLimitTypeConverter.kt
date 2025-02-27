package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.TicketLimit
import com.google.gson.Gson

class TicketLimitTypeConverter {
    @TypeConverter
    fun fromString(value: String?): TicketLimit? {
        return value?.let {
            Gson().fromJson(value, TicketLimit::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: TicketLimit?): String {
        return Gson().toJson(value)
    }
}