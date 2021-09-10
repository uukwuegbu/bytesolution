package com.ugogineering.android.bytesolution.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.ugogineering.android.bytesolution.R
import com.ugogineering.android.bytesolution.databinding.FragmentAdminSignupBinding
import com.ugogineering.android.bytesolution.model.AdminSignupBody
import com.ugogineering.android.bytesolution.viewmodel.AdminSignupViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [AdminSignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminSignupFragment : Fragment() {

    private lateinit var binding: FragmentAdminSignupBinding
    // Creating a reference to the AdminSignupViewModel
    private lateinit var viewModel: AdminSignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_admin_signup, container, false)
        // Initializing the viewmodel object
        viewModel = ViewModelProvider(this).get(AdminSignupViewModel::class.java)
        // Setting the viewmodel for databinding - this allows the bound layout access to all the data in the ViewModel
        binding.viewModel = viewModel
        // Specifying the fragment view as the lifecycle owner of the binding. This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signupButton.setOnClickListener {
            if(validateData()) {
                viewModel.signup((AdminSignupBody(binding.firstName.text.toString().trim(),
                    binding.lastName.text.toString(), binding.gender.text.toString(),
                    binding.dob.text.toString(), binding.address.text.toString(),
                    binding.country.text.toString(), binding.username.text.toString(),
                    binding.password.text.toString().trim(), binding.image.text.toString()
                )))
//                viewModel.signup((AdminSignupBody(binding.firstName.text.toString().trim(),
//                    binding.lastName.text.toString(), binding.gender.text.toString(),
//                    binding.dob.text.toString(), binding.address.text.toString(),
//                    binding.country.text.toString(), binding.username.text.toString(),
//                    binding.password.text.toString().trim(), binding.image.text.toString()
//                    )))
            }
        }

        binding.signupBackButton.setOnClickListener {
            goBackToHome()
        }

        return binding.root
    }

    private fun validateData(): Boolean {
        if(binding.firstName.text.toString().isEmpty()) {
            showMessage("Please enter your first name")
            return false
        }
        if(binding.lastName.text.toString().isEmpty()) {
            showMessage("Please enter your last name")
            return false
        }
        if(binding.gender.text.toString().isEmpty()) {
            showMessage("Please enter your email")
            return false
        }
        if(binding.dob.text.toString().isEmpty()) {
            showMessage("Please enter your phone number")
            return false
        }
        if(binding.address.text.toString().isEmpty()) {
            showMessage("Please enter your password")
            return false
        }
        if(binding.country.text.toString().isEmpty()){
            showMessage("The country you entered is not valid")
            return false
        }
        if(binding.password.text.toString().trim() != binding.passwordAgain.text.toString().trim())
        {
            showMessage("The password you re-entered does not match")
            return false
        }
        return true

    }

    private fun goBackToHome() {
        val action = AdminSignupFragmentDirections.actionAdminSignupFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.signupButton.isEnabled = false
    }

    private fun endProgress() {
        binding.progressBar.visibility = View.GONE
        binding.signupButton.isEnabled = true
    }

    private fun showMessage(s: String) {
        Snackbar.make(binding.rootLayout, s, Snackbar.LENGTH_LONG)
            .show()
    }

}