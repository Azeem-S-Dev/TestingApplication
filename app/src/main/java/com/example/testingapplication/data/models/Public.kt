package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Public(
    val endDateTime: String,
    val startDateTime: String,
    val startTBA: Boolean,
    val startTBD: Boolean
) : Parcelable