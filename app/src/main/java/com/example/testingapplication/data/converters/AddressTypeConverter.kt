package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.Address
import com.google.gson.Gson

class AddressTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Address? {
        return value?.let {
            Gson().fromJson(value, Address::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: Address?): String {
        return Gson().toJson(value)
    }
}