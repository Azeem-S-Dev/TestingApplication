package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AgeRestrictions(
    val id: String,
    val legalAgeEnforced: Boolean
) : Parcelable