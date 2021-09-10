package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        binding.buttonAdminSignup.setOnClickListener {
            goToAdminSignUp()
        }
        binding.buttonAdminSignin.setOnClickListener {
            goToAdminSignIn()
        }
        binding.buttonEmployeeSignup.setOnClickListener {
            goToEmployeeSignUp()
        }
        binding.buttonEmployees.setOnClickListener {
            goToEmployeeList()
        }
        binding.buttonCountries.setOnClickListener {
            goToCountriesList()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun goToCountriesList() {
        val action = HomeFragmentDirections.actionHomeFragmentToCountriesListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToEmployeeList() {
        val action = HomeFragmentDirections.actionHomeFragmentToEmployeeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToEmployeeSignUp() {
        val action = HomeFragmentDirections.actionHomeFragmentToEmployeeSignupFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToAdminSignIn() {
        val action = HomeFragmentDirections.actionHomeFragmentToAdminSigninFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToAdminSignUp() {
        val action = HomeFragmentDirections.actionHomeFragmentToAdminSignupFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}