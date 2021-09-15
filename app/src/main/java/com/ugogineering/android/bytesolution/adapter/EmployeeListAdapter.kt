package com.ugogineering.android.bytesolution.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.model.EmployeeList

class EmployeeListAdapter: RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {
    var data = listOf<EmployeeList.EmployeeResult?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // val res holds a reference to the resources for this view
        val res = holder.itemView.context.resources
        holder.employeeId.text = item?.id.toString()
        holder.firstname.text = item?.firstname
        holder.lastname.text = item?.lastname
        holder.gender.text = item?.gender
        holder.dob.text = item?.dob
        holder.address.text = item?.address
        holder.country.text = item?.country
        holder.designation.text = item?.designation
        if(item?.id != null) {
            val number = item.id
            if(number % 2 == 0){
                holder.itemView.setBackgroundColor(res.getColor(R.color.grey))
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_employee, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val employeeId: TextView = itemView.findViewById(R.id.employee_id)
        val firstname: TextView = itemView.findViewById(R.id.firstname)
        val lastname: TextView = itemView.findViewById(R.id.lastname)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val dob: TextView = itemView.findViewById(R.id.dob)
        val address: TextView = itemView.findViewById(R.id.address)
        val country: TextView = itemView.findViewById(R.id.country)
        val designation: TextView = itemView.findViewById(R.id.designation)
    }
}