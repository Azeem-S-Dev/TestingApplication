package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Self
import com.google.gson.Gson

class SelfTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Self? {
        return value?.let {
            Gson().fromJson(value, Self::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Self?): String {
        return Gson().toJson(value)
    }
}