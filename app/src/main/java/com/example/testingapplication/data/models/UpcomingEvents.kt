package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class UpcomingEvents(
    val _filtered: Int,
    val _total: Int,
    val archtics: Int? = null,
    val ticketmaster: Int,
    val tmr: Int
) : Parcelable