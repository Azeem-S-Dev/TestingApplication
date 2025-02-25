package com.example.testingapplication.repository

import com.example.testingapplication.models.Country
import com.example.testingapplication.models.EventsData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("events.json?page=1&size=20&apikey=DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")
    suspend fun getAllEvents(): Response<EventsData>
}