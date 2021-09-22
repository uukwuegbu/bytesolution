package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentEmployeeDetailBinding
import com.ugogineering.android.bytesolution.viewmodel.EmployeeDetailViewModel
import com.ugogineering.android.bytesolution.viewmodel.EmployeeDetailViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class EmployeeDetailFragment : Fragment() {
    //private lateinit var binding: FragmentEmployeeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
//        binding = DataBindingUtil.inflate(inflater,
//            R.layout.fragment_employee_detail, container, false)
        val binding = FragmentEmployeeDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        // Get the selected Employee object from the Safe Args
        val employeeDetail = EmployeeDetailFragmentArgs.fromBundle(requireArguments()).selectedEmployee
        val viewModelFactory = employeeDetail?.let { EmployeeDetailViewModelFactory(it, application) }
        binding.employeeDetailViewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(EmployeeDetailViewModel::class.java)
        }


        binding.buttonBack.setOnClickListener {
            goBackToHome()
        }

        return binding.root
    }

    private fun goBackToHome() {
        val action = EmployeeDetailFragmentDirections.actionEmployeeDetailFragmentToEmployeeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}