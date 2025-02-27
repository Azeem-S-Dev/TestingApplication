package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Links(
    val attractions: List<AttractionX>? = emptyList(),
    val self: Self,
    val venues: List<VenueX>? = emptyList()
) : Parcelable