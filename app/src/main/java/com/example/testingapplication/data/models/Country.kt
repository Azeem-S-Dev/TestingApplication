package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Country(
    val countryCode: String,
    val name: String
) : Parcelable