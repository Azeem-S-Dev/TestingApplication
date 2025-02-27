package com.example.testingapplication.repository

import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.models.Embedded
import com.example.testingapplication.data.models.EventsData
import com.example.testingapplication.utils.ApiKey
import com.example.testingapplication.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil,
    private val eventDao: EventDao
) {
    private val apikey = ApiKey.API_KEY

    suspend fun getAllEvents(
        page: Int,
        size: Int,
        callBack: (isSuccess: Boolean, eventsData: EventsData?, message: String) -> Unit
    ) {
        if (!networkUtil.isInternetAvailable()) {
            getAllEventsLocally { isSuccess, eventsData, message ->
                if (isSuccess) {
                    callBack(isSuccess, eventsData, message)
                } else {
                    callBack(false, null, "No Internet Connection")
                }
            }
            return
        }

        try {
            val response = apiService.getAllEvents(
                page = page,
                size = size,
                apikey = apikey
            )
            if (response.isSuccessful) {
                response.body()?.let { eventData ->
                    callBack(true, eventData, "")
                    eventDao.insertEvents(eventData._embedded.events)
                }
                println("Events Data from API")
            } else {
                getAllEventsLocally { isSuccess, eventsData, message ->
                    callBack(isSuccess, eventsData, message)
                }
            }
        } catch (e: Exception) {
            getAllEventsLocally { isSuccess, eventsData, message ->
                callBack(isSuccess, eventsData, message)
            }
        }
    }

    private fun getAllEventsLocally(
        callBack: (isSuccess: Boolean, eventsData: EventsData?, message: String) -> Unit
    ) {
        println("Events Data from Room DB")
        try {
            val cachedEvents = eventDao.getAllEvents()
            if (!cachedEvents.isNullOrEmpty()) {
                callBack(
                    true,
                    EventsData(
                        _embedded = Embedded(events = cachedEvents),
                        page = null
                    ), ""
                )
            } else {
                println("Events Data from Room DB: No events found")
                callBack(false, null, "No events found")
            }
        } catch (e: Exception) {
            println("Events Data from Room DB: Error getting events")
            callBack(false, null, "Error getting events")
        }
    }

}