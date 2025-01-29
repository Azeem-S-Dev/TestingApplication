package com.example.testingapplication.models

data class Event(
    val _embedded: EmbeddedX,
    val _links: LinksXX,
    val accessibility: Accessibility,
    val ageRestrictions: AgeRestrictions,
    val classifications: List<ClassificationX>,
    val dates: Dates,
    val id: String,
    val images: List<ImageXX>,
    val locale: String,
    val name: String,
    val priceRanges: List<PriceRange>,
    val products: List<Product>,
    val promoter: Promoter,
    val promoters: List<Promoter>,
    val sales: Sales,
    val seatmap: Seatmap,
    val test: Boolean,
    val ticketLimit: TicketLimit,
    val ticketing: Ticketing,
    val type: String,
    val url: String
)