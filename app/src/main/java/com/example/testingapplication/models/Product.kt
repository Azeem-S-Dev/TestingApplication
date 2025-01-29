package com.example.testingapplication.models

data class Product(
    val classifications: List<ClassificationX>,
    val id: String,
    val name: String,
    val type: String,
    val url: String
)