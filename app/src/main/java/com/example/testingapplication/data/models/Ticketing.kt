package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Ticketing(
    val allInclusivePricing: AllInclusivePricing,
    val id: String,
    val safeTix: SafeTix
) : Parcelable