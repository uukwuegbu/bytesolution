package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentEmployeeSignupBinding


/**
 * A simple [Fragment] subclass.
 */
class EmployeeSignupFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_employee_signup, container, false)

        binding.signupBackButton.setOnClickListener {
            goBackToHome()
        }

        return binding.root
    }

    private fun goBackToHome() {
        val action = EmployeeSignupFragmentDirections.actionEmployeeSignupFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}