package com.ugogineering.android.bytesolution.model

data class AdminSignupBody(
    val firstname: String, val lastname: String,
    val gender: String, val dob: String,
    val address: String, val country: String,
    val username: String, val password: String,
    val image: String
)
