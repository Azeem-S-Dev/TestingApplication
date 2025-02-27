package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class State(
    val name: String,
    val stateCode: String
) : Parcelable