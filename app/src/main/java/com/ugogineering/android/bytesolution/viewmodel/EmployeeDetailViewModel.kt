package com.ugogineering.android.bytesolution.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ugogineering.android.bytesolution.model.EmployeeList

class EmployeeDetailViewModel(
    @Suppress("UNUSED_PARAMETER")
    employeeDetail: EmployeeList.EmployeeResult, app: Application
): AndroidViewModel(app) {

    private val _selectedEmployee = MutableLiveData<EmployeeList.EmployeeResult>()
    val selectedEmployee: LiveData<EmployeeList.EmployeeResult>
        get() = _selectedEmployee

    init {
        _selectedEmployee.value = employeeDetail
    }
}