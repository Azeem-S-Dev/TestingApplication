package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class GeneralInfo(
    val childRule: String,
    val generalRule: String
) : Parcelable