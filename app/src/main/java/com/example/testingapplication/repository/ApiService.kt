package com.example.testingapplication.repository

import com.example.testingapplication.models.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v3.1/all")
    suspend fun getAllCountries(): Response<List<Country>>
}