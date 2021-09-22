package com.ugogineering.android.bytesolution.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ugogineering.android.bytesolution.model.EmployeeList

@BindingAdapter("idString")
fun TextView.setId(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.id.toString()
    }

}
//@BindingAdapter("employeeListData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<EmployeeList.EmployeeResult>) {
//
//}

@BindingAdapter("firstname")
fun TextView.setFirstname(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.firstname
    }
}
@BindingAdapter("lastname")
fun TextView.setLastname(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.lastname
    }
}
@BindingAdapter("gender")
fun TextView.setGender(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.gender
    }
}
@BindingAdapter("dob")
fun TextView.setDob(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.dob
    }
}
@BindingAdapter("address")
fun TextView.setAddress(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.address
    }
}
@BindingAdapter("country")
fun TextView.setCountry(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.country
    }
}
@BindingAdapter("designation")
fun TextView.setDesignation(item: EmployeeList.EmployeeResult) {
    item?.let {
        text = item.designation
    }
}
