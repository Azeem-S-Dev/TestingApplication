package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class EmbeddedX(
    val attractions: List<Attraction>,
    val venues: List<Venue>
) : Parcelable