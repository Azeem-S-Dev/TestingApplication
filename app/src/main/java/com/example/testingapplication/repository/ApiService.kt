package com.example.testingapplication.repository

import com.example.testingapplication.data.models.EventsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("events.json")
    suspend fun getAllEvents(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("apikey") apikey: String
    ): Response<EventsData>
}