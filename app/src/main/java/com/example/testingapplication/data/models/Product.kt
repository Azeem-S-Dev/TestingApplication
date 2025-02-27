package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Product(
    val classifications: List<ClassificationX>,
    val id: String,
    val name: String,
    val type: String,
    val url: String
) : Parcelable