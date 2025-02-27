package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Presale(
    val endDateTime: String,
    val name: String,
    val startDateTime: String
) : Parcelable