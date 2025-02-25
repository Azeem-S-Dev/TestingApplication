package com.example.testingapplication.repository

import com.example.testingapplication.models.EventsData
import com.example.testingapplication.utils.NetworkUtil
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil
) {
    private val apikey = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"

    suspend fun getAllEvents(
        page: Int,
        size: Int,
        callBack: (isSuccess: Boolean, eventsData: EventsData?, message: String) -> Unit
    ) {
        if (!networkUtil.isInternetAvailable()) {
            callBack(false, null, "No Internet Connection")
            return
        }

        try {
            val response = apiService.getAllEvents(
                page = page,
                size = size,
                apikey = apikey
            )
            if (response.isSuccessful) {
                callBack(true, response.body(), "")
            } else {
                val err = response.errorBody()
                callBack(false, null, response.message())
                println("API error =>  ${err}")
            }
        } catch (e: Exception) {
            callBack(false, null, "Data fetching failed")
        }
    }

}