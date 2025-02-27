package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Accessibility
import com.google.gson.Gson

class AccessibilityTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Accessibility? {
        return value?.let {
            Gson().fromJson(value, Accessibility::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Accessibility?): String {
        return Gson().toJson(value)
    }
}