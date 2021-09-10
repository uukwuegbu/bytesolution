package com.ugogineering.android.bytesolution.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugogineering.android.bytesolution.model.Countries
import com.ugogineering.android.bytesolution.model.CountriesList
import com.ugogineering.android.bytesolution.model.EmployeeList
import com.ugogineering.android.bytesolution.network.Api
import com.ugogineering.android.bytesolution.network.Api2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CountriesListViewModel: ViewModel() {
    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val status: LiveData<String>
        get() = _status

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    // Live data for list of only countries
    private val _countries = MutableLiveData<List<CountriesList.Countries?>>()
    val countries: LiveData<List<CountriesList.Countries?>>
        get() = _countries

    // Live data for list of countries fetched from API
    private val _countriesList = MutableLiveData<CountriesList>()
    val countriesList: LiveData<CountriesList>
        get() = _countriesList

    // Adding a coroutine job
    private var viewModelJob = Job()
    // Creating a coroutine scope for the new job using the main dispatcher
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    /*
    * Call getCountriesList Immediately on init so that we can display status immediately.
     */
    init {
        //
        getCountriesList()
    }

    private fun getCountriesList() {
        coroutineScope.launch {
            var getCountriesListDeferred = Api2.retrofitService2.getCountries()
            try {
                var listResult = getCountriesListDeferred.await()
                _countriesList.value = listResult
                _countries.value = listResult.result
                _response.value = "Success. Size ${listResult.result.size} . Code ${listResult.code}: "

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}