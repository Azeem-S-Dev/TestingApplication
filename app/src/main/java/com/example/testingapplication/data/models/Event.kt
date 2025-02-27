package com.example.testingapplication.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "events", primaryKeys = ["id"])
data class Event(
    val _embedded: EmbeddedX,
    val _links: Links,
    val accessibility: Accessibility? = null,
    val ageRestrictions: AgeRestrictions? = null,
    val classifications: List<ClassificationX>,
    val dates: Dates,
    val id: String,
    val images: List<ImageX>,
    val locale: String,
    val name: String,
    val priceRanges: List<PriceRange>? = emptyList(),
    val products: List<Product>? = emptyList(),
    val promoter: Promoter? = null,
    val promoters: List<Promoter>? = emptyList(),
    val sales: Sales,
    val seatmap: Seatmap,
    val test: Boolean,
    val ticketLimit: TicketLimit? = null,
    val ticketing: Ticketing,
    val type: String,
    val url: String
) : Parcelable