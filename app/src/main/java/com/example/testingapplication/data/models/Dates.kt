package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Dates(
    val spanMultipleDays: Boolean,
    val start: Start,
    val status: Status,
    val timezone: String
) : Parcelable