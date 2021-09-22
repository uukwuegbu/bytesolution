package com.ugogineering.android.bytesolution.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeResultList(
    val id: Int, val firstname: String,
    val lastname: String, val gender: String,
    val dob: String, val address: String,
    val country: String, val designation: String
): Parcelable
