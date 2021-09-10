package com.ugogineering.android.bytesolution.model

data class EmployeeList(
    val status: String, val result: List<EmployeeResult?>?,
    val response: String
) {
    data class EmployeeResult (
        val id: Int, val firstname: String,
        val lastname: String, val gender: String,
        val dob: String, val address: String,
        val country: String, val designation: String)
}
