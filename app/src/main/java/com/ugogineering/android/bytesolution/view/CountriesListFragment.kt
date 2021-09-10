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
import com.ugogineering.android.bytesolution.databinding.FragmentCountriesListBinding
import com.ugogineering.android.bytesolution.viewmodel.CountriesListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CountriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesListFragment : Fragment() {

    private lateinit var binding: FragmentCountriesListBinding
    private val viewModel: CountriesListViewModel by lazy {
        ViewModelProvider(this).get(CountriesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_list, container, false)
        // Setting the viewmodel for databinding - this allows the bound layout access to all the data in the ViewModel
        binding.viewModel = viewModel
        // Specifying the fragment view as the lifecycle owner of the binding. This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner


        binding.buttonBack.setOnClickListener {
            goBackToHome()
        }
        binding.buttonNext.setOnClickListener {
            goToCountryDetails()
        }


        return binding.root
    }

    private fun goBackToHome() {
        val action = CountriesListFragmentDirections.actionCountriesListFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToCountryDetails() {
        val action = CountriesListFragmentDirections.actionCountriesListFragmentToCountryDetailFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}