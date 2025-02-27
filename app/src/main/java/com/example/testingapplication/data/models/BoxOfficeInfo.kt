package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class BoxOfficeInfo(
    val openHoursDetail: String,
    val phoneNumberDetail: String,
    val willCallDetail: String
) : Parcelable