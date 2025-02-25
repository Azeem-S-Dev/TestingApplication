package com.example.testingapplication.models

data class Attraction(
    val _links: Links,
    val aliases: List<String>,
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
)