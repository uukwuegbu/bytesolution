package com.ugogineering.android.bytesolution.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ugogineering.android.bytesolution.model.EmployeeList

class EmployeeDetailViewModelFactory(
        private val employeeDetail: EmployeeList.EmployeeResult,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeDetailViewModel::class.java)) {
            return EmployeeDetailViewModel(employeeDetail, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}