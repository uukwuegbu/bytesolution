package com.ugogineering.android.bytesolution.model

data class EmployeeSignupBody(
    val firstname: String, val lastname: String,
    val gender: String, val dob: String,
    val address: String, val country: String,
    val designation: String, val image: String
)
