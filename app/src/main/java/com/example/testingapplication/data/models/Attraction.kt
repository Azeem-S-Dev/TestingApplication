package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Attraction(
    val _links: Links,
    val aliases: List<String>? = emptyList(),
    val classifications: List<ClassificationX>,
    val externalLinks: ExternalLinks,
    val id: String,
    val images: List<ImageX>,
    val locale: String,
    val name: String,
    val test: Boolean,
    val type: String,
    val upcomingEvents: UpcomingEvents,
    val url: String
) : Parcelable