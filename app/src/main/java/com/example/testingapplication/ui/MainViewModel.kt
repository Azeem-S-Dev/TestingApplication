package com.example.testingapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapplication.models.Country
import com.example.testingapplication.models.Event
import com.example.testingapplication.models.EventsData
import com.example.testingapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _originalEventsListData = MutableLiveData<EventsData>()
    private val _eventsLiveData = MutableLiveData<List<Event>>()
    val eventsLiveData: LiveData<List<Event>> get() = _eventsLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: MutableLiveData<String?> get() = _errorLiveData

    fun getAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllEvents(
                page = 0,
                size = 20
            ) { isSuccess, eventsData, message ->
                if (isSuccess) {
                    eventsData?.let {
                        _originalEventsListData.postValue(it)
                        _eventsLiveData.postValue(it._embedded.events)
                        _errorLiveData.postValue(null)
                    }
                } else {
                    _errorLiveData.postValue(message)
                }
            }

        }
    }

    fun searchEvents(query: String) {
        val originalList = _originalEventsListData.value?._embedded?.events ?: emptyList()
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