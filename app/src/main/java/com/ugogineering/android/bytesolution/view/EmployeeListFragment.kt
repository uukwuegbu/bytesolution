package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.adapter.EmployeeListAdapter
import com.ugogineering.android.bytesolution.databinding.FragmentEmployeeListBinding
import com.ugogineering.android.bytesolution.model.EmployeeList
import com.ugogineering.android.bytesolution.viewmodel.EmployeeListViewModel


/**
 * A simple [Fragment] subclass.
 */
class EmployeeListFragment : Fragment() {

    private lateinit var binding: FragmentEmployeeListBinding
    private val viewModel: EmployeeListViewModel by lazy {
        ViewModelProvider(this).get(EmployeeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false)
        // Setting the viewmodel for databinding - this allows the bound layout access to all the data in the ViewModel
        binding.viewModel = viewModel
        // Specifying the fragment view as the lifecycle owner of the binding. This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner


        binding.buttonNext.setOnClickListener {
            goToEmployeeDetails()
        }
        binding.buttonBack.setOnClickListener {
            goBackToHome()
        }

        val adapter = EmployeeListAdapter()
        binding.employeesList.adapter = adapter
        // Creating an observer on the employeeList variable. By supplying the fragment's
        // viewLifecycleOwner as the lifecycle owner we can make sure this observer is only
        // active when the RecyclerView is on the screen.
        viewModel.employeeList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    private fun goBackToHome() {
        val action = EmployeeListFragmentDirections.actionEmployeeListFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToEmployeeDetails() {
        val action = EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}