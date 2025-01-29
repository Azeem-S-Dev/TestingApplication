package com.example.testingapplication.repository

import com.example.testingapplication.models.Country
import javax.inject.Inject
import javax.inject.Singleton


class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchAllCountries(): List<Country> {
        try {
            val response = apiService.getAllCountries()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            println("API error =>  ${e.message}")  // Pass the exception to be handled later
            return listOf()
        }
    }

}