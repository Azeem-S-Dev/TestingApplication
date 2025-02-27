package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ImageX(
    val attribution: String? = null,
    val fallback: Boolean? = null,
    val height: Int,
    val ratio: String? = null,
    val url: String? = null,
    val width: Int
) : Parcelable