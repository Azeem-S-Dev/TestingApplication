package com.example.testingapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapplication.models.Country
import com.example.testingapplication.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>> get() = _countriesLiveData

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val countries = repository.fetchAllCountries()
            _countriesLiveData.postValue(countries)
        }
    }

}