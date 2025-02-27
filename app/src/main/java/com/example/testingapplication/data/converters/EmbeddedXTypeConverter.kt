package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.EmbeddedX
import com.google.gson.Gson

class EmbeddedXTypeConverter {
    @TypeConverter
    fun fromString(value: String?): EmbeddedX? {
        return value?.let {
            Gson().fromJson(value, EmbeddedX::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: EmbeddedX?): String {
        return Gson().toJson(value)
    }
}