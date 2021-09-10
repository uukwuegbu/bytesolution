package com.ugogineering.android.bytesolution.model

data class CountriesList(
    val code : Int,
    val result : List<Countries?>,
    val extra : List<String?>
) {
    data class Countries(
        val name : String?,
        val code : String?,
        val states : List<States?>?
    ) {
        data class States(
            val code: String?,
            val name: String?
        )
    }
}