package com.ugogineering.android.bytesolution.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.ListItemEmployeeBinding
import com.ugogineering.android.bytesolution.model.EmployeeList

class EmployeeListAdapter(val clickListener: EmployeeListListener):
    ListAdapter<EmployeeList.EmployeeResult,
        EmployeeListAdapter.ViewHolder>(EmployeeListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemEmployeeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: EmployeeList.EmployeeResult?,
            clickListener: EmployeeListListener
        ) {
            // val res holds a reference to the resources for this view
            val res = itemView.context.resources
            binding.employee = item
            binding.clickListener = clickListener
            binding.executePendingBindings()

            if (item?.id != null) {
                val number = item.id
                if (number % 2 == 0) {
                    itemView.setBackgroundColor(res.getColor(R.color.grey))
                } else {
                    itemView.setBackgroundColor(Color.WHITE)
                }
            }
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater
//                    .inflate(R.layout.list_item_employee, parent, false)
                val binding = ListItemEmployeeBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class EmployeeListDiffCallback: DiffUtil.ItemCallback<EmployeeList.EmployeeResult?>() {
    override fun areItemsTheSame(
        oldItem: EmployeeList.EmployeeResult,
        newItem: EmployeeList.EmployeeResult
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EmployeeList.EmployeeResult,
        newItem: EmployeeList.EmployeeResult
    ): Boolean {
        return oldItem == newItem
    }
}
class EmployeeListListener(val clickListener: (employee: EmployeeList.EmployeeResult) -> Unit) {
    fun onClick(employee: EmployeeList.EmployeeResult) = clickListener(employee)
}
//class EmployeeListListener(val clickListener: (employeeID: Int) -> Unit) {
//    fun onClick(employee: EmployeeList.EmployeeResult) = clickListener(employee.id)
//}