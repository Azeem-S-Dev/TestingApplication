package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Sales(
    val presales: List<Presale>? = emptyList(),
    val `public`: Public? = null
) : Parcelable