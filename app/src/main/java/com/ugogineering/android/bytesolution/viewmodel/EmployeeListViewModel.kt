package com.ugogineering.android.bytesolution.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ugogineering.android.bytesolution.formatEmployeeResultList
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

    val employeeListString = Transformations.map(employeeList) { employeeList ->
        formatEmployeeResultList(employeeList)
    }

    // Live data for list of employees
    private val _employeeResultList = MutableLiveData<List<EmployeeResultList?>?>()
    val employeeResultList: LiveData<List<EmployeeResultList?>?>
        get() = _employeeResultList

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
                //_employeeResultList.value = listResult.result
                _response.value = "Success. Response: ${listResult.response} . Status: ${listResult.status} And size is ${listResult.result?.size}"
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