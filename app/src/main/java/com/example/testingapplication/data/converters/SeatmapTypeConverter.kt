package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Seatmap
import com.google.gson.Gson

class SeatmapTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Seatmap? {
        return value?.let {
            Gson().fromJson(value, Seatmap::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Seatmap?): String {
        return Gson().toJson(value)
    }
}