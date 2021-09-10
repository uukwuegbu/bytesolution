package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentEmployeeDetailBinding


/**
 * A simple [Fragment] subclass.
 */
class EmployeeDetailFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_employee_detail, container, false)

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