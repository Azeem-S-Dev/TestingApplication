package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SubGenre(
    val id: String,
    val levelType: String? = null,
    val name: String
) : Parcelable