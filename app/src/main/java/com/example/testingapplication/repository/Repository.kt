package com.example.testingapplication.repository

import com.example.testingapplication.models.EventsData
import javax.inject.Inject


class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllEvents(
        callBack: (isSuccess: Boolean, eventsData: EventsData?, message: String) -> Unit
    ) {
        try {
            val response = apiService.getAllEvents()
            if (response.isSuccessful) {
                callBack(true, response.body(), "")
            } else {
                val err = response.errorBody()
                callBack(false, null, response.message())
//                throw Exception("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            println("API error =>  ${e.message}")  // Pass the exception to be handled later
        }
    }

}