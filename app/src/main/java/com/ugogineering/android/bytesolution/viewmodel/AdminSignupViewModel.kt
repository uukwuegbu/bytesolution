package com.ugogineering.android.bytesolution.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugogineering.android.bytesolution.model.AdminSignupBody
import com.ugogineering.android.bytesolution.model.AdminSignupResponse
import com.ugogineering.android.bytesolution.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AdminSignupViewModel: ViewModel() {
    // The internal MutableLiveData object that stores the signup response
    private val _signupResponse = MutableLiveData<AdminSignupResponse>()
    // The external immutable LiveData for the response string
    val signupResponse: LiveData<AdminSignupResponse>
        get() = _signupResponse

    // Signup Status
    private val _signupStatus = MutableLiveData<String>()
    val signupStatus: LiveData<String>
        get() = _signupStatus

    // Signup Message
    private val _signupMessage = MutableLiveData<String>()
    val signupMessage: LiveData<String>
        get() = _signupMessage

    init {
        _signupMessage.value = "Default signup message"
        _signupStatus.value = "No"
    }

    // Adding a coroutine job
    private var viewModelJob = Job()
    // Creating a coroutine scope for the new job using the main dispatcher
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    // Sets the value of the signupResponse LiveData to the Login API signup response
    fun signup(adminSignupBody: AdminSignupBody) {
        coroutineScope.launch {
            val signupDeferred = Api.retrofitService.adminSignup(adminSignupBody.firstname, adminSignupBody.lastname,
                adminSignupBody.gender, adminSignupBody.dob, adminSignupBody.address, adminSignupBody.country,
                adminSignupBody.username, adminSignupBody.password, adminSignupBody.image
            )
            try {
                val signupResult = signupDeferred.await()
                _signupMessage.value = "Success: ${signupResult.response}"
                _signupStatus.value = signupResult.status
                _signupResponse.value = signupResult

            } catch (e: Exception) {
                _signupMessage.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}