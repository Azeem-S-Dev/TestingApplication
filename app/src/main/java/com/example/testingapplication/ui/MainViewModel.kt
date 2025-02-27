package com.example.testingapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.models.Country
import com.example.testingapplication.data.models.Event
import com.example.testingapplication.data.models.EventsData
import com.example.testingapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var _originalEventsListData: EventsData? = null
    private val _eventsLiveData = MutableLiveData<List<Event>>()
    val eventsLiveData: LiveData<List<Event>> get() = _eventsLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: MutableLiveData<String?> get() = _errorLiveData

    fun getAllEvents() {
        viewModelScope.launch(dispatcher) {
            repository.getAllEvents(
                page = 0,
                size = 40
            ) { isSuccess, eventsData, message ->
                if (isSuccess) {
                    eventsData?.let {
                        _eventsLiveData.postValue(it._embedded.events)
                        _originalEventsListData = it
                        _errorLiveData.postValue(null)
                    }
                } else {
                    _errorLiveData.postValue(message)
                }
            }

        }
    }

    fun searchEvents(query: String) {
        val originalList = _originalEventsListData?._embedded?.events ?: emptyList()
        val filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { event ->
                event.name.contains(query, ignoreCase = true) ||
                        event._embedded.venues.any { venue ->
                            venue.name.contains(query, ignoreCase = true)
                        }
            }
        }
        _eventsLiveData.postValue(filteredList)
    }

}