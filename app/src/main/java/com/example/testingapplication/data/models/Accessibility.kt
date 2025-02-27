package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Accessibility(
    val id: String,
    val ticketLimit: Int
) : Parcelable