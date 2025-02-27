package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Promoter(
    val description: String,
    val id: String,
    val name: String
) : Parcelable