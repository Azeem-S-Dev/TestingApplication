package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class PriceRange(
    val currency: String,
    val max: Double,
    val min: Double,
    val type: String
) : Parcelable