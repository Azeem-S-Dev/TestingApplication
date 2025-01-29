package com.example.testingapplication.models

data class PriceRange(
    val currency: String,
    val max: Double,
    val min: Double,
    val type: String
)