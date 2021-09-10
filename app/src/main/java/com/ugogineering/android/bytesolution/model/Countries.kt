package com.ugogineering.android.bytesolution.model

data class Countries(
    val name : String?,
    val code : String?,
    val states : States?
) {
    data class States(
        val code: String?,
        val name: String?
    )
}
