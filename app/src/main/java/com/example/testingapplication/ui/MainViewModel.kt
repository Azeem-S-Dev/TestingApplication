package com.example.testingapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapplication.models.Country
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

    private val _eventsLiveData = MutableLiveData<EventsData>()
    val eventsLiveData: LiveData<EventsData> get() = _eventsLiveData

    fun getAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllEvents() { isSuccess, eventsData, message ->
                if (isSuccess) {
                    eventsData?.let {
                        _eventsLiveData.postValue(it)
                    }
                }
            }

        }
    }

}