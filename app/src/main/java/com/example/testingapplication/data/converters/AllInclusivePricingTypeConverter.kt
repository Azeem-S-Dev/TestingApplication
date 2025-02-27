package com.example.testingapplication.data.converters

import androidx.room.TypeConverter
import com.example.testingapplication.data.models.AllInclusivePricing
import com.google.gson.Gson

class AllInclusivePricingTypeConverter {
    @TypeConverter
    fun fromString(value: String?): AllInclusivePricing? {
        return value?.let {
            Gson().fromJson(value, AllInclusivePricing::class.java)
        }
    }

    @TypeConverter
    fun fromObject(value: AllInclusivePricing?): String {
        return Gson().toJson(value)
    }
}