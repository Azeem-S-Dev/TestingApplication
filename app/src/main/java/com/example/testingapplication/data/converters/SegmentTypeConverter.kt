package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Segment
import com.google.gson.Gson

class SegmentTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Segment? {
        return value?.let {
            Gson().fromJson(value, Segment::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Segment?): String {
        return Gson().toJson(value)
    }
}