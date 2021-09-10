package com.ugogineering.android.bytesolution.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugogineering.android.bytesolution.model.EmployeeList
import com.ugogineering.android.bytesolution.model.EmployeeResultList
import com.ugogineering.android.bytesolution.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EmployeeListViewModel: ViewModel() {

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

    // Live data for list of employees
    private val _employees = MutableLiveData<EmployeeList>()
    val employees: LiveData<EmployeeList>
        get() = _employees

    private val _employeeList = MutableLiveData<List<EmployeeList.EmployeeResult?>?>()
    val employeeList: LiveData<List<EmployeeList.EmployeeResult?>?>
        get() = _employeeList

    // Adding a coroutine job
    private var viewModelJob = Job()
    // Creating a coroutine scope for the new job using the main dispatcher
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    /*
    * Call getEmployeeListImmediately on init so that we can display status immediately.
     */
    init {
        //_employees.value = null
        getEmployeeList()
    }

    private fun getEmployeeList() {
        coroutineScope.launch {
            var getEmployeeListDeferred = Api.retrofitService.getEmployees()
            try {
                var listResult = getEmployeeListDeferred.await()
                _employees.value = listResult
                _employeeList.value = listResult.result
                _response.value = "Success. Response: ${listResult.response} . Status: ${listResult.status} . List is ${listResult.result}. And size is ${listResult.result?.size}" // "Success: ${listResult.size} Employees retrieved"
            } catch (e: Exception) {
                //_employees.value = EmployeeList("no", "no", "no") // List<EmployeeList> //ArrayList()
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}